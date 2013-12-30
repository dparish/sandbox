package dparish.test.inject;

import java.lang.reflect.Constructor;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.testing.CountingEventBus;
import dparish.client.presenter.MainPresenter;
import dparish.client.view.MainView;
import org.jukito.BindingsCollector;
import org.jukito.JukitoModule;
import org.jukito.TestScope;

/**
 * @author dparish
 *
 * This module is my test at abstracting out the Jukito initializations so that I could run my tests
 * without a JukitoRunner.
 *
 */
public class SandboxTestModule extends JukitoModule {
    @Override
    protected void configureTest() {
        bind(MainView.Presenter.class).to(MainPresenter.class).in(TestScope.SINGLETON);
        bind(EventBus.class).to(CountingEventBus.class).in(TestScope.SINGLETON);
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
        BindingsCollector collector = null;
        try {
            // All this is because the constructor for BindingsCollector is packaged protected.
            Class collectorClass = Class.forName("org.jukito.BindingsCollector");
            Class[] args = new Class[1];
            args[0] = AbstractModule.class;
            Constructor constructor = collectorClass.getDeclaredConstructor(args);
            constructor.setAccessible(true);
            collector = (BindingsCollector) constructor.newInstance(this);

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        collector.collectBindings();
        setBindingsObserved(collector.getBindingsObserved());
    }

}
