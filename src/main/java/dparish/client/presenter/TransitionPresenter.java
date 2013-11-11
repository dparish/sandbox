package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.transition.TransitionView;

/**
 * @author dparish
 */
@Singleton
public class TransitionPresenter extends BasePresenter<TransitionView> implements TransitionView.Presenter {

    @Inject
    public TransitionPresenter(TransitionView view) {
        super(view);
        view.setPresenter(this);
    }
}
