package dparish.client.util;

/**
 * @author dparish
 */
public class Logger {

    public static void debug(String msg) {
        doLog("DEBUG:" + msg);
    }

    public static void error(String msg) {
        doLog("ERROR:" + msg);
    }

    private static native final void doLog(String msg) /*-{
        console.log(msg);
    }-*/;
}
