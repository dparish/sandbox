package dparish.client.view.directory;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;
import dparish.shared.models.Person;

/**
 * Created by david.parish on 4/29/14.
 */
@ImplementedBy(AddPersonWidget.class)
public interface AddPersonView extends View {

    interface Presenter {
        void personAdded(Person person);
    }

    void setPresenter(Presenter presenter);

    void addSucceeded();
    void addFailed(String message);
}
