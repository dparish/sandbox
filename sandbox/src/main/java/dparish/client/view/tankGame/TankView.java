package dparish.client.view.tankGame;

import com.google.inject.ImplementedBy;
import dparish.client.presenter.TankPresenter;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(TankViewImpl.class)
public interface TankView extends View {

    void setPresenter(Presenter presenter);

    interface Presenter {
        TankSprite getTank(int tank);
        void turnLeft();
        void turnRight();
        void reverse();
        TankPresenter.Coordinates getNextPosition(int x, int y);
    }
}
