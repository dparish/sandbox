package dparish.client.view.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * @author dparish
 */
public interface CalendarResources extends ClientBundle {

    public static CalendarResources INSTANCE = GWT.create(CalendarResources.class);

    @ClientBundle.Source("calendar.css")
    CalendarCSS calendarCss();
}
