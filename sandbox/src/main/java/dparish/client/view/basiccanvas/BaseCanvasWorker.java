package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author dparish
 */
public class BaseCanvasWorker {

    protected Canvas canvas;
    protected Context2d context;

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
}
