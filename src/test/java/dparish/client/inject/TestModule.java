package dparish.client.inject;

import dparish.client.presenter.MainPresenter;
import dparish.client.view.MainView;
import org.jukito.JukitoModule;

/**
 * @author dparish
 */
public class TestModule extends JukitoModule {
    @Override
    protected void configureTest() {
        bind(MainView.Presenter.class).to(MainPresenter.class);
    }
}
