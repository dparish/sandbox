package dparish.client.view.transition;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(TransitionViewImpl.class)
public interface TransitionView extends View {

    interface Presenter {

    }

    void setPresenter(Presenter presenter);
}
