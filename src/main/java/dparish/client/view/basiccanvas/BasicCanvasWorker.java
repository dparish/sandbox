package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author dparish
 */
public class BasicCanvasWorker extends BaseCanvasWorker {

    public BasicCanvasWorker(Canvas canvas) {
        super(canvas);
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

    public void rotateCenter() {
        context.setFillStyle("black");
        context.fillRect(20,20,25,25);

        context.setTransform(1,0,0,1,0,0);
        Double angleInRadians = 45 * Math.PI / 180;
        int x = 100;
        int y = 100;
        int width = 50;
        int height = 50;
        context.translate(x + .5*width, y+.5*height);
        context.rotate(angleInRadians);
        context.setFillStyle("red");
        context.fillRect(-.5*width, -.5*height, width, height);
    }
}
