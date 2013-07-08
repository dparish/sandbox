package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author dparish
 */
public class BasicCanvasWorker {

    private Canvas canvas;
    private Context2d context;

    public BasicCanvasWorker(Canvas canvas) {
        this.canvas = canvas;
        context = canvas.getContext2d();
    }

    public void simpleRotate() {
        context.setFillStyle("black");
        context.fillRect(20,20,25,25);

        context.setTransform(1,0,0,1,0,0);
        Double angleInRadians = 45 * Math.PI / 180;
        context.rotate(angleInRadians);

        context.setFillStyle("red");
        context.fillRect(100,100,50,50);
    }

    public void text() {
        context.setFillStyle("#aaaaaa");
        context.fillRect(0,0,200,00);
        context.setFillStyle("#000000");
        context.setFont("20px _sans");
        context.setTextBaseline(Context2d.TextBaseline.TOP);
        context.fillText("Canvas!", 0,0);
    }

    public void clear() {
        // This resets the rotation.
        context.setTransform(1,0,0,1,0,0);

        // This clears the canvas
        context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());

    }
}
