package dparish.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.DateTimeFormatInfo;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import dparish.client.util.ContextAwareRepeatingCommand;
import dparish.client.util.Logger;

/**
 * @author dparish
 */
public class WelcomeViewImpl extends Composite implements WelcomeView {
    interface WelcomeViewImplUiBinder extends UiBinder<FlowPanel, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder ourUiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    @UiField
    Label currentLocale;

    @UiField
    Label startDay;

    @Inject
    public WelcomeViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        currentLocale.setText(LocaleInfo.getCurrentLocale().getLocaleName());
        DateTimeFormatInfo dateTimeFormatInfo = LocaleInfo.getCurrentLocale().getDateTimeFormatInfo();
        String[] weekDays = dateTimeFormatInfo.weekdaysFull();
        startDay.setText(weekDays[dateTimeFormatInfo.firstDayOfTheWeek()]);
    }

    /**
     * Get the widget to use to display this view.
     *
     * @return The widget.
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void render() {
        Scheduler.get().scheduleFixedDelay(new ContextAwareRepeatingCommand() {
            @Override
            public boolean run() {
                Logger.debug("running scheduled task");
                return true;
            }
        }, 5000);

    }
}