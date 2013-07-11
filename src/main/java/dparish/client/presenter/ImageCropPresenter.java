package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.imagecrop.ImageCropView;

/**
 * @author dparish
 */
@Singleton
public class ImageCropPresenter extends BasePresenter<ImageCropView> implements ImageCropView.Presenter {

    @Inject
    public ImageCropPresenter(ImageCropView view) {
        super(view);
        view.setPresenter(this);
    }
}
