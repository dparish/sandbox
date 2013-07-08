package dparish.client.inject;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import dparish.client.presenter.MainPresenter;

/**
 * @author dparish
 */
@GinModules(SandboxInjectModule.class)
public interface SandboxInjector extends Ginjector {
    MainPresenter getMainPresenter();
}

