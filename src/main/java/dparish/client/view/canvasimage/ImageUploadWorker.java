package dparish.client.view.canvasimage;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import dparish.client.view.basiccanvas.BaseCanvasWorker;
import dparish.client.widgets.FileReader;
import dparish.client.widgets.NativeFile;
import dparish.client.widgets.ProgressCallback;
import dparish.client.widgets.ProgressEvent;

/**
 * @author David Parish dparish
 */
public class ImageUploadWorker extends BaseCanvasWorker {

    public ImageUploadWorker(Canvas canvas) {
        super(canvas);
    }

    public void handleUpload(final NativeFile file) {
        if (file == null) {
            GWT.log("file is null");
            return;
        }
        GWT.log("file name:" + file.getName());
        FileReader reader = FileReader.create();
        reader.readAsDataURL(file, new ProgressCallback() {
            @Override
            public void onError(ProgressEvent e) {
                GWT.log("Error with upload");
            }

            @Override
            public void onLoad(ProgressEvent e) {
                GWT.log("upload success");
                onUploadSuccess(file, e.getResult());
            }
        });
    }

    private void onUploadSuccess(NativeFile file, String data) {
        if (data == null) {
            GWT.log("file is empty");
            return;
        }
        Image image = new Image();
        image.setUrl(data);
        ImageElement imageElement = ImageElement.as(image.getElement());
        clear();
        context.drawImage(imageElement, 0 ,0);
    }
}
