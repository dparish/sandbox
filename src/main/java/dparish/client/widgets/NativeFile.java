package dparish.client.widgets;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author aarnold
 */
public class NativeFile extends JavaScriptObject {

    protected NativeFile() {}

    public final native String getName() /*-{
        return this.name;
    }-*/;

    public final native String getBase64data() /*-{
        return this.base64data;
    }-*/;

    public final native String getSize() /*-{
        return this.size.toString();
    }-*/;

    public final native String getType() /*-{
        return this.type;
    }-*/;

    public final String getFileExtension() {
        final String name = getName();
        String[] tokens = name.split("\\.");
        if (tokens != null) {
            return tokens[tokens.length - 1];
        } else {
            return name;
        }
    }

    public final boolean hasImageExtension() {
        final String fileExtension = getFileExtension();
        String[] extensions = {"png", "gif", "jpeg", "jpg"};
        for (String extension : extensions) {
            if (fileExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}