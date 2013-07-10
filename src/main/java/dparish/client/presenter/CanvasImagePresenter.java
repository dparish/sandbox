package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.canvasimage.CanvasImageView;
import dparish.client.widgets.NativeFile;

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

    @Override
    public void uploadSuccess(NativeFile file, String data) {
        view().renderImage(file,data);
    }
}
