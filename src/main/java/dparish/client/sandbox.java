package dparish.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import dparish.client.inject.SandboxInjector;
import dparish.client.presenter.MainPresenter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class sandbox implements EntryPoint {

    private final SandboxInjector injectModule = GWT.create(SandboxInjector.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
      MainPresenter presenter = injectModule.getMainPresenter();
      presenter.go(RootLayoutPanel.get());
  }
}
