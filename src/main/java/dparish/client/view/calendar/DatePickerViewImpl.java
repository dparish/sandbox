package dparish.client.view.calendar;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class DatePickerViewImpl extends Composite implements DatePickerView {
    interface CalendarViewImplUiBinder extends UiBinder<FlowPanel, DatePickerViewImpl> {
    }

    private static CalendarViewImplUiBinder ourUiBinder = GWT.create(CalendarViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    CustomDatePicker datePicker;

    @Inject
    public DatePickerViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        styleCalendar();
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
        presenter.handleNewMonth(datePicker.getCurrentMonth());
        datePicker.setPresenter(p);
    }

    /**
     * Enable or disable the date in the date picker calendar view.
     */
    @Override
    public void setEnabledOnDate(boolean enabled, Date dayToChange) {
        datePicker.getDefaultCalendarView().setEnabledOnDate(enabled, dayToChange);
    }

    @Override
    public void addMonths(int numMonths) {
        datePicker.getCustomMonthSelector().addMonths(numMonths);
    }

    /**
     * Set's the enabled or disabled status of the forward button.
     */
    @Override
    public void setForwardButtonEnabled(boolean isEnabled) {
        datePicker.getCustomMonthSelector().setForwardButtonEnabled(isEnabled);
    }

    private void styleCalendar() {
        CalendarResources.INSTANCE.calendarCss().ensureInjected();
    }


}
