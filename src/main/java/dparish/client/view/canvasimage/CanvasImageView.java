package dparish.client.view.canvasimage;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;
import dparish.client.widgets.NativeFile;

/**
 * @author David Parish dparish
 */
@ImplementedBy(CanvasImageViewImpl.class)
public interface CanvasImageView extends View {

    void renderImage(NativeFile file, String data);

    interface Presenter {
        void uploadSuccess(NativeFile file, String data);

    }

    void setPresenter(Presenter presenter);
}
