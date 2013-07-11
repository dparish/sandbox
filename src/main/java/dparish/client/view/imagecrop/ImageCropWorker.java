package dparish.client.view.imagecrop;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import dparish.client.resources.canvas.CanvasResources;
import dparish.client.view.basiccanvas.BaseCanvasWorker;

/**
 * @author dparish
 */
public class ImageCropWorker extends BaseCanvasWorker {

    public ImageCropWorker(Canvas canvas) {
        super(canvas);
    }

    public void render(final Image image) {
        image.setUrl(CanvasResources.INSTANCE.mini().getSafeUri());
        image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                context.drawImage(ImageElement.as(image.getElement()), 0 ,0);
            }
        });
    }
}
