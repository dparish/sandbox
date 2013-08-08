package dparish.client.view.calendar;

import com.google.gwt.user.datepicker.client.CalendarModel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.datepicker.client.DefaultCalendarView;

/**
 * @author dparish
 * created so we could customize the date picker more easily.
 */
public class CustomDatePicker extends DatePicker {

    public CustomDatePicker() {
        super(new CustomMonthSelector(), new DefaultCalendarView(), new CalendarModel());
    }
}
