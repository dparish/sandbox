package dparish.client.view.canvasimage;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import dparish.client.view.basiccanvas.BaseCanvasWorker;
import dparish.client.widgets.FileReader;
import dparish.client.widgets.NativeFile;
import dparish.client.widgets.ProgressCallback;
import dparish.client.widgets.ProgressEvent;

/**
 * @author David Parish dparish
 */
public class ImageUploadWorker extends BaseCanvasWorker {

    CanvasImageView.Presenter presenter;

    public ImageUploadWorker(Canvas canvas, CanvasImageView.Presenter presenter) {
        super(canvas);
        this.presenter = presenter;
    }

    /**
     * Reads the file from the browser via the html5 file reader. Then calls the onLoad callback upon success.
     */
    public void handleUpload(final NativeFile file) {
        if (file == null) {
            GWT.log("file is null");
            return;
        }
        FileReader reader = FileReader.create();
        reader.readAsDataURL(file, new ProgressCallback() {
            @Override
            public void onError(ProgressEvent e) {
                GWT.log("Error with upload");
            }

            @Override
            public void onLoad(ProgressEvent e) {
                onUploadSuccess(file, e.getResult());
            }
        });
    }

    private void onUploadSuccess(NativeFile file, String data) {
        if (data == null) {
            GWT.log("file is empty");
            return;
        }
        presenter.uploadSuccess(file, data);
    }
}
