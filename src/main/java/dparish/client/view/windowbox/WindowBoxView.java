package dparish.client.view.windowbox;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(WindowBoxViewImpl.class)
public interface WindowBoxView extends View {

    interface Presenter {

    }

    void setPresenter(Presenter presenter);
}
