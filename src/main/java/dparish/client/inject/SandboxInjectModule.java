package dparish.client.inject;

import com.google.gwt.inject.client.AbstractGinModule;
import dparish.client.presenter.MainPresenter;
import dparish.client.view.MainView;

/**
 * @author dparish
 */
public class SandboxInjectModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainView.Presenter.class).to(MainPresenter.class);
    }
}
