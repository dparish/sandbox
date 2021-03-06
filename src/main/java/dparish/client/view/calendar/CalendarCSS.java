package dparish.client.view.calendar;

import com.google.gwt.resources.client.CssResource;

/**
 * @author dparish
 */
public interface CalendarCSS extends CssResource {
    String datePickerDay();

    String nextButton();

    String monthDisplay();


    String monthHeading();

    String datePickerDayIsWeekend();

    String datePickerDayIsFiller();

    String datePickerWeekdayLabel();

    String datePickerWeekendLabel();

    String datePickerDayIsValueAndHighlighted();

    String datePickerDayIsHighlighted();

    String datePickerDayIsDisabled();

    String datePickerDayIsValue();

    String datePickerDayIsToday();

    String disabled();
}
