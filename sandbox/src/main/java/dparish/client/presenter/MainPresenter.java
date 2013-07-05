package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.MainView;
import dparish.client.view.Page;

/**
 * @author dparish
 */
@Singleton
public class MainPresenter extends BasePresenter<MainView> implements MainView.Presenter {

    private WelcomePresenter welcomePresenter;

    @Inject
    public MainPresenter(MainView view, WelcomePresenter welcomePresenter) {
        super(view);
        this.welcomePresenter = welcomePresenter;
        view.setPresenter(this);
        onPageSelected(Page.WELCOME);
    }
    @Override
    public void onPageSelected(Page page) {
        switch (page) {
            case WELCOME:
                welcomePresenter.go(view().getContentPanel());
                break;
        }
    }
}
