package dparish.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.tankGame.TankSprite;
import dparish.client.view.tankGame.TankView;

/**
 * @author dparish
 */
@Singleton
public class TankPresenter extends BasePresenter<TankView> implements TankView.Presenter {

    public static int CANVAS_MAX = 500;

    public enum Orientation {
        UP(0),
        DOWN(180),
        LEFT(270),
        RIGHT(90);

        Orientation(int angle) {
            this.angle = angle;
        }

        private int angle;

        double getRadian() {
            return angle * Math.PI / 180;
        }
    }

    public static class Coordinates {
        public int x;
        public int y;
    }

    private Orientation orientation = Orientation.UP;

    @Inject
    public TankPresenter(TankView view) {
        super(view);
        view.setPresenter(this);
    }

    @Override
    public TankSprite getTank(int tank) {
        if (tank <1 || tank > 8) {
            GWT.log(tank + " is not a valid tank number");
            return null;
        }
        TankSprite tankSprite = new TankSprite();
        if (tank < 8) {
            tankSprite.x = (tank * 32);
            tankSprite.y = 0;
        } else {
            tankSprite.x = 0;
            tankSprite.y = 33;
        }
        GWT.log("ORIENTATION:" + orientation + " radian:" + orientation.getRadian());

        tankSprite.angle = orientation.getRadian();

        return tankSprite;
    }

    @Override
    public void turnLeft() {
        switch (orientation) {
            case UP:
                orientation = Orientation.LEFT;
                break;
            case DOWN:
                orientation = Orientation.RIGHT;
                break;
            case LEFT:
                orientation = Orientation.DOWN;
                break;
            case RIGHT:
                orientation = Orientation.UP;
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (orientation) {
            case UP:
                orientation = Orientation.RIGHT;
                break;
            case DOWN:
                orientation = Orientation.LEFT;
                break;
            case LEFT:
                orientation = Orientation.UP;
                break;
            case RIGHT:
                orientation = Orientation.DOWN;
                break;
        }
    }

    @Override
    public void reverse() {
        switch (orientation) {
            case UP:
                orientation = Orientation.DOWN;
                break;
            case DOWN:
                orientation = Orientation.UP;
                break;
            case LEFT:
                orientation = Orientation.RIGHT;
                break;
            case RIGHT:
                orientation = Orientation.LEFT;
                break;
        }
    }

    @Override
    public Coordinates getNextPosition(int x, int y) {
        switch (orientation) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        // Change to get the real values.
        if (x > CANVAS_MAX - 32) {
            x = CANVAS_MAX - 32;
        }
        if (x < 0) {
            x = 0;
        }
        if (y > CANVAS_MAX - 32) {
            y = CANVAS_MAX - 32;
        }
        if (y < 0) {
            y = 0;
        }
        Coordinates coordinates = new Coordinates();
        coordinates.x = x;
        coordinates.y = y;
        return coordinates;
    }
}
