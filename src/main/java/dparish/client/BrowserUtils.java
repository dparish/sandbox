package dparish.client;

import com.google.gwt.dom.client.Element;

/**
 * Collection of helper methods for the browser.
 *
 * @author dparish
 *
 * From: http://stackoverflow.com/questions/8249880/how-to-add-css-animationend-event-handler-to-gwt-widget
 */
public class BrowserUtils {

    public static native void registerAnimationHandler(final Element element, CssEventHandler handler)  /*-{
        var callback = function() {
            handler.@dparish.client.CssEventHandler::onCssEnd()();
        }
        element.addEventListener("webkitAnimationEnd", callback, false); // Webkit
        element.addEventListener("animationend", callback, false); // Mozilla
    }-*/;

    public static native void registerTransitionHandler(final Element element, CssEventHandler handler)  /*-{
        var callback = function() {
            handler.@dparish.client.CssEventHandler::onCssEnd()();
        }
        element.addEventListener("webkitTransitionEnd", callback, false); // Webkit
        element.addEventListener("transitionend", callback, false); // Mozilla
    }-*/;
}
