package dparish.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import dparish.client.view.Page;

/**
 * @author dparish
 *         \
 */
public interface MainMessages extends Messages {

    public static MainMessages INSTANCE = GWT.create(MainMessages.class);

    @DefaultMessage("Hi and welcome to the sandbox")
    String welcome();

    @DefaultMessage("Welcome")
    @AlternateMessage({
            "WELCOME", "Welcome",
            "BASIC_CANVAS", "Basic Canvas"
    })
    String navLabel(@Select Page page);

    @Messages.DefaultMessage("Rotate")
    String rotate();

    @Messages.DefaultMessage("Text Test")
    String textTest();

}
