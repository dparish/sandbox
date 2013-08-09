package dparish.client.presenter;

import java.util.Date;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import dparish.client.view.calendar.DatePickerView;

/**
 * Unlike most presenters this is NOT a singleton because there can be more than one date picker widget in the app
 * or on the view.
 * @author dparish
 */
public class DatePickerPresenter extends BasePresenter<DatePickerView> implements DatePickerView.Presenter {

    @Inject
    public DatePickerPresenter(DatePickerView view) {
        super(view);
        view.setPresenter(this);
    }

    /**
     * Disable the dates in the calendar if they are after the current date;
     *
     * @param currentMonth The current month displayed in the date picker.
     */
    @Override
    public void handleNewMonth(Date currentMonth) {
        Date nextDate = new Date();
        if (currentMonth.getMonth() != nextDate.getMonth()) {
            view().setForwardButtonEnabled(true);
            return;
        }
        view().setForwardButtonEnabled(false);
        // next date is now plus one day.
        CalendarUtil.addDaysToDate(nextDate, 1);

        Date lastDate = view().getLastDateShown();
        // last date goes up one because the loop below needs to test the last day as well.
        CalendarUtil.addDaysToDate(lastDate, 1);
        while (!CalendarUtil.isSameDate(lastDate, nextDate)) {
            view().setEnabledOnDate(false, nextDate);
            CalendarUtil.addDaysToDate(nextDate,1);
        }
    }

    private boolean isSameMonthAndYear(Date date) {
        Date now = new Date();
        if (now.getYear() != date.getYear()) {
            return false;
        }
        return (date.getMonth() == now.getMonth());
    }
    /**
     * Handles when forward is clicked.
     *
     * @param currentlyDisplayedMonth The currently displayed month (NOT the one you get when you click next)
     */
    @Override
    public void handleForwardClicked(Date currentlyDisplayedMonth) {
        if (isSameMonthAndYear(currentlyDisplayedMonth)) {
            // there is nothing to do, we are already on the present.
            return;
        }
        view().addMonths(+1);
    }
}
