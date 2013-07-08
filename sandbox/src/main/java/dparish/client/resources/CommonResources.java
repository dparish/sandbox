package dparish.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * @author dparish
 */
public interface CommonResources extends ClientBundle {

    CommonResources INSTANCE = GWT.create(CommonResources.class);

    @Source("common.css")
    CommonCSS css();
}
