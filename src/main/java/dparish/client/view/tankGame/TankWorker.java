package dparish.client.view.tankGame;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import dparish.client.presenter.TankPresenter;
import dparish.client.resources.canvas.CanvasResources;
import dparish.client.view.basiccanvas.BaseCanvasWorker;

/**
 * @author dparish
 */
public class TankWorker extends BaseCanvasWorker {

    private ImageElement tanks;
    private int[] animationFrames = new int[] {1,2,3,4,5,6,7,8};
    private int frameIndex = 0;
    private int dx = 0;
    private int dy = -1;
    private int x = 50;
    private int y = 50;

    private TankView.Presenter presenter;

    private Timer timer;

    public TankWorker(TankView.Presenter presenter, Canvas canvas) {
        super(canvas);
        this.presenter = presenter;

        // Now we need to change the image bundle to a ImageElement
        Image image = new Image(CanvasResources.INSTANCE.tanksSheet().getSafeUri());
        tanks = ImageElement.as(image.getElement());
        timer = new Timer() {
            @Override
            public void run() {
                drawFrame();
            }
        };
    }

    public void draw() {
        timer.scheduleRepeating(100);
        drawFrame();
    }

    public void cancel() {
        timer.cancel();
    }

    private void drawFrame() {
        frameIndex++;
        context.setFillStyle("#aaaaaa");
        context.fillRect(0,0,500,500);
        // context.save();
        TankPresenter.Coordinates coordinates = presenter.getNextPosition(x,y);
        x = coordinates.x;
        y = coordinates.y;

        // This resets the rotation and clears, not sure if this is the right thing to do?
        clear();

        TankSprite tankSprite = presenter.getTank(frameIndex);
        // not sure if this is the right place??
        context.translate(x+16,y+16);
        context.rotate(tankSprite.angle);

        double sourceX = tankSprite.x;
        double sourceY = tankSprite.y;
        context.drawImage(tanks, sourceX,sourceY,32, 32,-16, -16 ,32,32);
        if (frameIndex == animationFrames.length) {
            frameIndex = 0;
        }
    }
}
