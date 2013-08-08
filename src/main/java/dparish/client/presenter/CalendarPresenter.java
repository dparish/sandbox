package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.calendar.CalendarView;

/**
 * @author dparish
 */
@Singleton
public class CalendarPresenter extends BasePresenter<CalendarView> implements CalendarView.Presenter {

    @Inject
    public CalendarPresenter(CalendarView view) {
        super(view);
    }
}
