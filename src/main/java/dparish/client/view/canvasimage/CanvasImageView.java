package dparish.client.view.canvasimage;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author David Parish dparish
 */
@ImplementedBy(CanvasImageViewImpl.class)
public interface CanvasImageView extends View {

    interface Presenter {

    }

    void setPresenter(Presenter presenter);
}
