package dparish.client.view.calendar;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.datepicker.client.CalendarModel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
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
    private Date lastSelectedDate = null;

    public CustomDatePicker() {
        super(new CustomMonthSelector(), new DefaultCalendarView(), new CalendarModel());
        defaultCalendarView = (DefaultCalendarView) getView();
        customMonthSelector = (CustomMonthSelector) getMonthSelector();

        // This value change listener makes sure you don't pick a
        // date that is after today.
        this.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date selected = event.getValue();
                Date now = new Date();
                // If the date is after today, we want to unselect it.
                if (CalendarUtil.isSameDate(selected, now)) {
                    // Same day, we will handle things after the if block.
                } else if (selected.after(now)) {
                    // next day or later. (the if above rules this out from triggering for later today)
                    if (lastSelectedDate == null) {
                        lastSelectedDate = now;
                    }
                    setValue(lastSelectedDate, true);
                    return;
                }
                lastSelectedDate = event.getValue();
            }
        });
    }

    /**
     * We need to make sure you can't set the month to after the current month.
     */
    @Override
    public void setCurrentMonth(Date month) {
        Date now = new Date();
        if (now.before(month) && (now.getMonth() < month.getMonth())) {
            // The date is after today AND it's a greater month, we don't want to do anything (don't set)
            return;
        }
        super.setCurrentMonth(month);
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
