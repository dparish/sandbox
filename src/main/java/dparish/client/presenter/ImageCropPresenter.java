package dparish.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.SandboxServiceAsync;
import dparish.client.view.imagecrop.ImageCropView;

/**
 * @author dparish
 */
@Singleton
public class ImageCropPresenter extends BasePresenter<ImageCropView> implements ImageCropView.Presenter {

    private SandboxServiceAsync sandboxService;

    @Inject
    public ImageCropPresenter(ImageCropView view, SandboxServiceAsync sandboxService) {
        super(view);
        view.setPresenter(this);
        this.sandboxService = sandboxService;
    }

    @Override
    public void saveImage(String data) {
        sandboxService.saveImageCrop(data, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                view().displaySaveMessage(caught.getMessage(), true);
            }

            @Override
            public void onSuccess(String result) {
                view().displaySaveMessage(result, false);
            }
        });
    }
}
