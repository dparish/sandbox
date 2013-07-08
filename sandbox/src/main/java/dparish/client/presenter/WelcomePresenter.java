package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.WelcomeView;

/**
 * @author dparish
 */
@Singleton
public class WelcomePresenter extends BasePresenter<WelcomeView> {

    @Inject
    public WelcomePresenter(WelcomeView view) {
        super(view);
    }
}
