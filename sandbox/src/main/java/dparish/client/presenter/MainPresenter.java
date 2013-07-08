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
    private BasicCanvasPresenter basicCanvasPresenter;

    @Inject
    public MainPresenter(MainView view, WelcomePresenter welcomePresenter, BasicCanvasPresenter basicCanvasPresenter) {
        super(view);
        this.welcomePresenter = welcomePresenter;
        this.basicCanvasPresenter = basicCanvasPresenter;

        view.setPresenter(this);
        onPageSelected(Page.WELCOME);
    }
    @Override
    public void onPageSelected(Page page) {
        switch (page) {
            case WELCOME:
                welcomePresenter.go(view().getContentPanel());
                break;
            case BASIC_CANVAS:
                basicCanvasPresenter.go(view().getContentPanel());
                break;
        }
    }
}
