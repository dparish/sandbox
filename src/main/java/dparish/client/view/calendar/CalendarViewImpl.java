package dparish.client.view.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class CalendarViewImpl extends Composite implements CalendarView {
    interface CalendarViewImplUiBinder extends UiBinder<FlowPanel, CalendarViewImpl> {
    }

    private static CalendarViewImplUiBinder ourUiBinder = GWT.create(CalendarViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    CustomDatePicker datePicker;

    @Inject
    public CalendarViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        styleCalendar();
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
    }

    private void styleCalendar() {
        CalendarResources.INSTANCE.calendarCss().ensureInjected();
    }
}
