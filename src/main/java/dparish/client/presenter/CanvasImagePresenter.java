package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.canvasimage.CanvasImageView;

/**
 * @author David Parish dparish
 */
@Singleton
public class CanvasImagePresenter extends BasePresenter<CanvasImageView> implements CanvasImageView.Presenter {

    @Inject
    public CanvasImagePresenter(CanvasImageView view) {
        super(view);
        view.setPresenter(this);
    }
}
