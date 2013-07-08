package dparish.client.view;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.ImplementedBy;

/**
 * @author dparish
 *
 */
@ImplementedBy(MainViewImpl.class)
public interface MainView extends View {

    void setPresenter (Presenter presenter);
    HasWidgets getContentPanel();

    interface Presenter {
        void onPageSelected(Page page);
    }
}
