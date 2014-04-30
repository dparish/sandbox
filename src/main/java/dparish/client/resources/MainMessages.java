package dparish.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;
import dparish.client.view.Page;

/**
 * @author dparish
 *
 */
public interface MainMessages extends Messages {

    public static MainMessages INSTANCE = GWT.create(MainMessages.class);

    @DefaultMessage("Hi and welcome to the sandbox")
    String welcome();

    @DefaultMessage("Welcome")
    @AlternateMessage({
            "WELCOME", "Welcome",
            "BASIC_CANVAS", "Basic Canvas",
            "TANK", "Tank Game",
            "CANVAS_IMAGE", "Canvas Image",
            "WINDOW_BOX", "Window Box",
            "IMAGE_CROP", "Image Crop",
            "CALENDAR", "Calendar",
            "TRANSITION", "CSS Events",
            "DIRECTORY", "Directory"
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

    @Messages.DefaultMessage("Drag and Drop")
    String dragAndDrop();

    @Messages.DefaultMessage("Upload File")
    String uploadFile();

    @Messages.DefaultMessage("Save Crop")
    String saveCrop();

    @Messages.DefaultMessage("Saved File: {0}")
    String savedFile(String fileName);

    @Messages.DefaultMessage("Error saving file:{0}")
    String errorSavingFile(String error);

    @Messages.DefaultMessage("Hover over me and I''ll disappear")
    String hoverOverMe();

    @Messages.DefaultMessage("Hover Over ended")
    String hoverOverEnded();

    @Messages.DefaultMessage("Hover out ended")
    String hoverOutEnded();

    @Messages.DefaultMessage("Click and I''ll move")
    String clickToMove();

    @Messages.DefaultMessage("I''m moving")
    String moving();

    @DefaultMessage("Please enter a first name")
    String enterFirstName();

    @Messages.DefaultMessage("Invalid phone format")
    String invalidPhoneFormat();

    @DefaultMessage("First Name")
    String firstName();

    @DefaultMessage("Last Name")
    String lastName();

    @DefaultMessage("Phone")
    String phone();
}
