package dparish.client.view.calendar;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(CalendarViewImpl.class)
public interface CalendarView extends View {

    public interface Presenter {
    }

    void setPresenter(Presenter p);
}

