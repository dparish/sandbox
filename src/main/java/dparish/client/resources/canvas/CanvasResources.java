package dparish.client.resources.canvas;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author dparish
 */
public interface CanvasResources extends ClientBundle {

    CanvasResources INSTANCE = GWT.create(CanvasResources.class);

    @ClientBundle.Source("tanks_sheet.png")
    ImageResource tanksSheet();
}
