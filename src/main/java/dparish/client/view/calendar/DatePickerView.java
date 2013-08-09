package dparish.client.view.calendar;

import java.util.Date;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(DatePickerViewImpl.class)
public interface DatePickerView extends View {

    public interface Presenter {
        /**
         * Disable the dates in the calendar if they are after the current date;
         *
         * @param currentMonth The current month displayed in the date picker.
         *
         */
        void handleNewMonth(Date currentMonth);

            /**
            * Handles when forward is clicked.
            * @param currentlyDisplayedMonth The currently displayed month (NOT the one you get when you click next)
            */
        void handleForwardClicked(Date currentlyDisplayedMonth);
    }

    void setPresenter(Presenter p);

    /**
     * Enable or disable the date in the date picker calendar view.
     */
    void setEnabledOnDate(boolean enabled, Date dayToChange);

    void addMonths(int numMonths);

    /**
     * Set's the enabled or disabled status of the forward button.
     */
    void setForwardButtonEnabled(boolean isEnabled);

    /**
     * Get the last date shown on the calendar.
     */
    Date getLastDateShown();
}

