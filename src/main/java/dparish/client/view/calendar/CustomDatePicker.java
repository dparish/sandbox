package dparish.client.view.calendar;

import com.google.gwt.user.datepicker.client.CalendarModel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.datepicker.client.DefaultCalendarView;

/**
 * @author dparish
 * created so we could customize the date picker more easily.
 */
public class CustomDatePicker extends DatePicker {

    private DefaultCalendarView defaultCalendarView;
    private CustomMonthSelector customMonthSelector;
    private DatePickerView.Presenter presenter;

    public CustomDatePicker() {
        super(new CustomMonthSelector(), new DefaultCalendarView(), new CalendarModel());
        defaultCalendarView = (DefaultCalendarView) getView();
        customMonthSelector = (CustomMonthSelector) getMonthSelector();
    }

    public void setPresenter(DatePickerView.Presenter presenter) {
        this.presenter = presenter;
        customMonthSelector.setPresenter(presenter);
    }

    public DefaultCalendarView getDefaultCalendarView() {
        return defaultCalendarView;
    }

    public CustomMonthSelector getCustomMonthSelector() {
        return customMonthSelector;
    }
}
