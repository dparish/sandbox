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
            "BASIC_CANVAS", "Basic Canvas",
            "TANK", "Tank Game"
    })
    String navLabel(@Select Page page);

    @Messages.DefaultMessage("Rotate")
    String rotate();

    @Messages.DefaultMessage("Text Test")
    String textTest();

    @Messages.DefaultMessage("Rotate Around Center")
    String rotateAroundCenter();

    @Messages.DefaultMessage("Tanks")
    String tanks();

    @Messages.DefaultMessage("Left")
    String left();

    @Messages.DefaultMessage("Right")
    String right();

    @Messages.DefaultMessage("Reverse")
    String reverse();

    @Messages.DefaultMessage("Start")
    String start();

    @Messages.DefaultMessage("Stop")
    String stop();
}
