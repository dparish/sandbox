package org.jukito;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dparish.client.presenter.MainPresenter;
import dparish.client.view.MainView;

/**
 * @author dparish
 *
 * This module is my test at abstracting out the Jukito initializations so that I could run my tests
 * without a JukitoRunner.
 *
 * I had to put this in the org.jukito package because the Constructor for BindingsCollector is
 * package protected.

 */
public class SandboxTestModule extends JukitoModule {
    @Override
    protected void configureTest() {
        bind(MainView.Presenter.class).to(MainPresenter.class);
    }

    @Provides
    @Singleton
    MainView mainView() {
        MainView view =  org.mockito.Mockito.mock(MainView.class);
        HasWidgets contentPanel = org.mockito.Mockito.mock(HasWidgets.class);
        org.mockito.Mockito.when(view.getContentPanel()).thenReturn(contentPanel);
        return view;
    }

    // This is mimicking what JukitoTestRunner does so we can test w/o the runner.
    public SandboxTestModule() {
        BindingsCollector collector = new BindingsCollector(this);
        collector.collectBindings();
        setBindingsObserved(collector.getBindingsObserved());
    }

}
