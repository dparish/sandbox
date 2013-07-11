package dparish.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author dparish
 *
 * A resizable window frame.
 * Must be posistioned relatively.
 */
public class SquareWindowPane extends FlowPanel {

    int lastMouseX;
    int lastMouseY;

    // True if the box is being moved (dragged)
    private boolean isBoxMoving = false;

    // True if the box has a corner dragged and it's being resized.
    private boolean isBoxResizing = false;

    // The corner that we are moving
    private CursorLocation activeCorner = CursorLocation.OUTSIDE_BOX;

    private Panel parentPanel;

    private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

    enum CursorLocation {
        TOP_LEFT_CORNER, TOP_RIGHT_CORNER, BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER, INSIDE_BOX, OUTSIDE_BOX;

        boolean isCorner() {
            switch (this) {
                case TOP_LEFT_CORNER:
                case TOP_RIGHT_CORNER:
                case BOTTOM_LEFT_CORNER:
                case BOTTOM_RIGHT_CORNER:
                    return true;
                case INSIDE_BOX:
                case OUTSIDE_BOX:
                    return false;
            }
            return false;
        }
    }

    @Override
    public void onUnload() {
        for (HandlerRegistration reg: registrations) {
            reg.removeHandler();
        }
        registrations.clear();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        parentPanel = (Panel) getParent();
        HandlerRegistration reg = parentPanel.addDomHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                lastMouseX = event.getClientX();
                lastMouseY = event.getClientY();
                CursorLocation cursorLocation = getCursorLocation(event);
                if (cursorLocation.isCorner()) {
                    activeCorner = cursorLocation;
                    setBoxResizing(true);
                    // We are in a corner, nothing to do.
                    // Will soon be resize
                } else if (cursorLocation == CursorLocation.INSIDE_BOX){
                    setBoxMoving(true);
                }
                event.preventDefault();
                event.stopPropagation();
            }
        }, MouseDownEvent.getType());
        registrations.add(reg);
        reg = parentPanel.addDomHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                if (isBoxMoving) {
                    setBoxMoving(false);
                    movePanel(event);
                }
                if (isBoxResizing) {
                    setBoxResizing(false);
                    resizePanel(event);
                }
            }
        },MouseUpEvent.getType());
        registrations.add(reg);

        reg = parentPanel.addDomHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                setCursor(event);
                if (isBoxMoving) {
                    parentPanel.getElement().getStyle().setCursor(Style.Cursor.MOVE);
                    movePanel(event);
                }
                if (isBoxResizing) {
                    resizePanel(event);
                }
                event.preventDefault();
                event.stopPropagation();
            }
        }, MouseMoveEvent.getType());
        registrations.add(reg);

    }

    public SquareWindowPane(int left, int top, int height) {
        setPanelPosition(left, top);
        setPanelDimensions(height);
        getElement().getStyle().setPosition(Style.Position.RELATIVE);
    }

    private void setCursor(MouseEvent event) {
        if (isBoxMoving || isBoxResizing) {
            // The pointer should already be correct, just keep it that way
            return;
        }
        switch (getCursorLocation(event)) {
            case TOP_LEFT_CORNER:
            case BOTTOM_RIGHT_CORNER:
                parentPanel.getElement().getStyle().setProperty("cursor", "nwse-resize");
                break;
            case TOP_RIGHT_CORNER:
            case BOTTOM_LEFT_CORNER:
                parentPanel.getElement().getStyle().setProperty("cursor", "nesw-resize");
                break;
            case INSIDE_BOX:
                parentPanel.getElement().getStyle().setCursor(Style.Cursor.MOVE);
                break;
            case OUTSIDE_BOX:
                parentPanel.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                break;
        }
    }

    private void setBoxResizing(boolean isResizing) {
        // just to be safe, turn off moving
        isBoxMoving = false;

        this.isBoxResizing = isResizing;
        if (!isResizing) {
            // No longer resizing, set back to original location.
            activeCorner = CursorLocation.OUTSIDE_BOX;
        }
    }

    private void setBoxMoving(boolean isMoving) {
        isBoxResizing = false;
        this.isBoxMoving = isMoving;
    }

    private int getPanelTop() {
        return getIntFromPx(getElement().getStyle().getTop());
    }

    private int getPanelLeft() {
        return getIntFromPx(getElement().getStyle().getLeft());
    }

    private int getPanelHeight() {
        return getIntFromPx(getElement().getStyle().getHeight());
    }

    private int getPanelWidth() {
        return getIntFromPx(getElement().getStyle().getWidth());
    }

    private void setPanelPosition (int left, int top) {
        GWT.log("left:" + left + " top:" + top);

        getElement().getStyle().setLeft(left, Style.Unit.PX);
        getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    /**
     * Since this is a square we will change the height and the width at the same time.
     */
    private void setPanelDimensions(int amount) {
        getElement().getStyle().setHeight(amount, Style.Unit.PX);
        getElement().getStyle().setWidth(amount, Style.Unit.PX);
    }



    /**
     * If we are in a corner, we don't want to drag and drop but to resize.
     * @return
     */
    private CursorLocation getCursorLocation(MouseEvent event) {
        // top left
        if (isCloseToCorner(event, getElement().getAbsoluteLeft(),
                getElement().getAbsoluteTop())) {
            return CursorLocation.TOP_LEFT_CORNER;
        }
        // bottom left
        if (isCloseToCorner(event, getElement().getAbsoluteLeft(),
                getElement().getAbsoluteBottom())) {
            return CursorLocation.BOTTOM_LEFT_CORNER;
        }
        // top right
        if (isCloseToCorner(event, getElement().getAbsoluteRight(),
                getElement().getAbsoluteTop())) {
            return CursorLocation.TOP_RIGHT_CORNER;
        }
        // bottom right
        if (isCloseToCorner(event, getElement().getAbsoluteRight(),
                getElement().getAbsoluteBottom())) {
            return CursorLocation.BOTTOM_RIGHT_CORNER;
        }
        boolean isXInside = (getElement().getAbsoluteLeft() < event.getClientX()
                && getElement().getAbsoluteRight() > event.getClientX());
        boolean isYInside = (getElement().getAbsoluteTop() < event.getClientY()
                && getElement().getAbsoluteBottom() > event.getClientY());
        if (isXInside && isYInside) {
            return CursorLocation.INSIDE_BOX;
        }
        return CursorLocation.OUTSIDE_BOX;
    }

    /**
     *
     * @param event
     * @param x the corner x coordinate
     * @param y the corner y coordinate
     * @return
     */
    private boolean isCloseToCorner(MouseEvent event, int x, int y) {
        int CURSOR_SLOP = 5;

        boolean isX = ( Math.abs(event.getClientX() - x) < CURSOR_SLOP);
        boolean isY = ( Math.abs(event.getClientY() - y) < CURSOR_SLOP);
        return (isX && isY);
    }

    /**
     * Does the work of moving the panel.
     * @param event
     */
    private void movePanel(MouseEvent event) {
        int newX = event.getClientX();
        int newY = event.getClientY();
        int left = getIntFromPx(getElement().getStyle().getLeft());
        int top = getIntFromPx(getElement().getStyle().getTop());
        left += (newX - lastMouseX);
        top += (newY - lastMouseY);
        setPanelPosition(left, top);
        lastMouseX = newX;
        lastMouseY = newY;
    }

    private class MoveHandler {

        boolean wasX = false;

        int moveAmount = 0;

        MoveHandler(int x, int y) {
            int absX = Math.abs(x);
            int absY = Math.abs(y);
            if (absX < absY) {
                moveAmount = x;
                wasX = true;
            }
            moveAmount = y;
            wasX = false;
        }

        public boolean didXMove() {
            return wasX;
        }

        public boolean didYMove() {
            return !wasX;
        }
    }

    /**
     * Does the work of resizing the panel.
     */
    private void resizePanel(MouseEvent event) {
        int newX = event.getClientX();
        int newY = event.getClientY();
        int xMoved = newX - lastMouseX;
        int yMoved = newY - lastMouseY;
        lastMouseX = newX;
        lastMouseY = newY;

        MoveHandler move = new MoveHandler(xMoved, yMoved);
        int left = getPanelLeft();
        int top = getPanelTop();

        GWT.log("in resize panel for:" + activeCorner);
        switch (activeCorner) {
            case TOP_LEFT_CORNER: {
                // The bottom right corner needs to stay the same so move, then change the dimensions based off
                // where we moved to compared to the opposite corner.
                // Move the panel to the new x and y, then resize the panel.
//                int newLeft = left + move.moveAmount;
//                int newTop = top + move.moveAmount;
//                setPanelPosition(newLeft, newTop);
//                setPanelDimensions(oppositeX - newLeft);
//                break;
                // left and top both change.
                // x and y both behave the same.
                int height = getPanelHeight();
                setPanelPosition(left + move.moveAmount, top + move.moveAmount);
                height-= move.moveAmount;
                // why minus, because an increase in size is a negative change to x;
                setPanelDimensions(height);
                break;
            }

            case TOP_RIGHT_CORNER: {
                // This moves the panel up the correct amount
                // Since the left corner bottom corner should never move
                // we don't change left EVER. We change top and the height.
                int height = getPanelHeight();
                if (move.wasX) {
                    // an increase here is positive to x
                    setPanelPosition(left, top + move.moveAmount);
                    height+= move.moveAmount;
                } else {
                    setPanelPosition(left, top + move.moveAmount);
                    height-= move.moveAmount;
                }
                // why minus, because an increase in size is a negative change to x;
                setPanelDimensions(height);
                break;
            }
            case BOTTOM_LEFT_CORNER: {
                // only the left can change.
                int height = getPanelHeight();
                if (move.wasX) {
                    // growth means x is negative and y is positive.
                    setPanelPosition(left + move.moveAmount, top);
                    height-= move.moveAmount;
                } else {
                    setPanelPosition(left - move.moveAmount, top);
                    height+= move.moveAmount;
                }
                // why minus, because an increase in size is a negative change to x;
                setPanelDimensions(height);
                break;
            }
            case BOTTOM_RIGHT_CORNER: {
                // only the left can change.
                int height = getPanelHeight();
                if (move.wasX) {
                    setPanelPosition(left, top);
                    height+= move.moveAmount;
                } else {
                    setPanelPosition(left, top);
                    height+= move.moveAmount;
                }
                // why minus, because an increase in size is a negative change to x;
                setPanelDimensions(height);
                break;
            }
            case INSIDE_BOX:
            case OUTSIDE_BOX:
                GWT.log("We should only be on a corner");
                break;
        }
    }

    private int getIntFromPx(String px) {
        px = px.replace("px","");
        return Integer.valueOf(px);
    }
}