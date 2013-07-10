package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;

/**
 * @author dparish
 */
public class BaseCanvasWorker {

    protected Canvas canvas;
    protected Context2d context;
    private boolean isDragging = false;
    private Rectangle rectangle = new Rectangle();

    public BaseCanvasWorker(Canvas canvas) {
        this.canvas = canvas;
        context = canvas.getContext2d();
    }

    public void clear() {
        // This resets the rotation.
        context.setTransform(1,0,0,1,0,0);

        // This clears the canvas
        context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
    }

    public Context2d getContext() {
        return context;
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void mouseMoved(MouseMoveEvent event) {
        if (isDragging) {
            int x = event.getX();
            int y = event.getY();
            rectangle.move(x,y);
        }
        event.preventDefault();
        event.stopPropagation();
    }

    public void mouseDown(MouseDownEvent event) {
        if (rectangle.isMouseInRectangle(event.getX(), event.getY())) {
            isDragging = true;
            rectangle.startDrag(event.getX(), event.getY());
        } else {
            isDragging = false;
        }
        event.preventDefault();
        event.stopPropagation();
    }

    public void mouseUp(MouseUpEvent event) {
        rectangle.move(event.getX(), event.getY());
        isDragging = false;
        event.preventDefault();
        event.stopPropagation();
    }

    private class Rectangle {
        int topX;
        int topY;
        int size = 100;

        int dragStartX;
        int dragStartY;

        public void draw() {
            context.setStrokeStyle("grey");
            context.strokeRect(topX,topY,size,size);
        }

        public void startDrag(int x, int y) {
            dragStartX = x;
            dragStartY = y;
        }

        public void move(int x, int y) {
            int xMoved = x - dragStartX;
            int yMoved = y - dragStartY;
            topX += xMoved;
            topY += yMoved;
            dragStartX = topX;
            dragStartY = topY;
            clear();
            draw();
        }

        public boolean isMouseInRectangle(int x, int y) {
            if (x > topX && x > topY && x < (x + size) && y < (y+size)) {
                return true;
            }
            return false;
        }

    }

}
