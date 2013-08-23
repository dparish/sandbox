package dparish.client.view.imagecrop;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(ImageCropImpl.class)
public interface ImageCropView extends View {

    interface Presenter {
        void saveImage(String data);
    }

    void setPresenter(Presenter p);
    void displaySaveMessage(String message, boolean isError);
}
