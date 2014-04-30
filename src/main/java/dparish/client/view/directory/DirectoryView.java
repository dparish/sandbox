package dparish.client.view.directory;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * Created by david.parish on 4/29/14.
 */
@ImplementedBy(DirectoryViewImpl.class)
public interface DirectoryView extends View{

    interface Presenter {
    }

    void setPresenter(Presenter presenter);

    HasWidgets getAddPersonContainer();
}
