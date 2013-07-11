package dparish.client.view.windowbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class WindowBoxViewImpl extends Composite implements WindowBoxView {
    interface WindowBoxViewImplUiBinder extends UiBinder<FlowPanel, WindowBoxViewImpl> {
    }

    private static WindowBoxViewImplUiBinder ourUiBinder = GWT.create(WindowBoxViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    SimplePanel draggablePanel;

    @UiField
    FlowPanel content;

    int lastMouseX;
    int lastMouseY;
    private boolean isBoxMoving = false;

    enum CursorLocation {
        TOP_LEFT_CORNER, TOP_RIGHT_CORNER, BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER, INSIDE_BOX, OUTSIDE_BOX
    }

    @Inject
    public WindowBoxViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        // Can't use draggable here as it cancels mouse events
        // and drag and drop doesn't give me any access to the drop final coordinates for placement and that SUCKS!
        // draggablePanel.getElement().setDraggable(Element.DRAGGABLE_TRUE);
        draggablePanel.getElement().getStyle().setLeft(10, Style.Unit.PX);
        draggablePanel.getElement().getStyle().setTop(10, Style.Unit.PX);
        draggablePanel.addDomHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                lastMouseX = event.getScreenX();
                lastMouseY = event.getScreenY();
                if (getCursorLocation(event) != CursorLocation.INSIDE_BOX) {
                    // We are in a corner, nothing to do.
                    // Will soon be resize
                } else {
                    setBoxMoving(true);
                }
                event.preventDefault();
                event.stopPropagation();;
            }
        }, MouseDownEvent.getType());
        content.addDomHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                if (isBoxMoving) {
                    setBoxMoving(false);
                    movePanel(event);
                }
            }
        },MouseUpEvent.getType());

        content.addDomHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                setCursor(event);
                if (isBoxMoving) {
                    content.getElement().getStyle().setCursor(Style.Cursor.MOVE);
                    movePanel(event);
                }
                event.preventDefault();
                event.stopPropagation();
            }
        }, MouseMoveEvent.getType());
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void setCursor(MouseEvent event) {
        if (isBoxMoving) {
            content.getElement().getStyle().setCursor(Style.Cursor.MOVE);
            return;
        }
        switch (getCursorLocation(event)) {
            case TOP_LEFT_CORNER:
            case BOTTOM_RIGHT_CORNER:
                content.getElement().getStyle().setProperty("cursor", "nwse-resize");
                break;
            case TOP_RIGHT_CORNER:
            case BOTTOM_LEFT_CORNER:
                content.getElement().getStyle().setProperty("cursor", "nesw-resize");
                break;
            case INSIDE_BOX:
                content.getElement().getStyle().setCursor(Style.Cursor.MOVE);
                break;
            case OUTSIDE_BOX:
                content.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                break;
        }
    }

    private void setBoxMoving(boolean isMoving) {
        if (isMoving == this.isBoxMoving) {
            // There was no change, nothing to do.
            return;
        }
        if (isMoving) {
            content.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        } else {
            GWT.log("cursor cleared");
            content.getElement().getStyle().clearCursor();
        }
        this.isBoxMoving = isMoving;
    }

    /**
     * If we are in a corner, we don't want to drag and drop but to resize.
     * @return
     */
    private CursorLocation getCursorLocation(MouseEvent event) {
        // top left
        if (isCloseToCorner(event, draggablePanel.getElement().getAbsoluteLeft(),
                draggablePanel.getElement().getAbsoluteTop())) {
            return CursorLocation.TOP_LEFT_CORNER;
        }
        // bottom left
        if (isCloseToCorner(event, draggablePanel.getElement().getAbsoluteLeft(),
                draggablePanel.getElement().getAbsoluteBottom())) {
            return CursorLocation.BOTTOM_LEFT_CORNER;
        }
        // top right
        if (isCloseToCorner(event, draggablePanel.getElement().getAbsoluteRight(),
                draggablePanel.getElement().getAbsoluteTop())) {
            return CursorLocation.TOP_RIGHT_CORNER;
        }
        // bottom right
        if (isCloseToCorner(event, draggablePanel.getElement().getAbsoluteRight(),
                draggablePanel.getElement().getAbsoluteBottom())) {
            return CursorLocation.BOTTOM_RIGHT_CORNER;
        }
        boolean isXInside = (draggablePanel.getElement().getAbsoluteLeft() < event.getClientX()
                && draggablePanel.getElement().getAbsoluteRight() > event.getClientX());
        boolean isYInside = (draggablePanel.getElement().getAbsoluteTop() < event.getClientY()
                && draggablePanel.getElement().getAbsoluteBottom() > event.getClientY());
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
        int newX = event.getScreenX();
        int newY = event.getScreenY();
        int left = getIntFromPx(draggablePanel.getElement().getStyle().getLeft());
        int top = getIntFromPx(draggablePanel.getElement().getStyle().getTop());
        left += (newX - lastMouseX);
        top += (newY - lastMouseY);
        draggablePanel.getElement().getStyle().setLeft(left, Style.Unit.PX);
        draggablePanel.getElement().getStyle().setTop(top, Style.Unit.PX);
        lastMouseX = newX;
        lastMouseY = newY;
    }

    private int getIntFromPx(String px) {
        px = px.replace("px","");
        return Integer.valueOf(px);
    }
}