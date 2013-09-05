package dparish.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dparish.client.view.MainView;
import dparish.client.view.Page;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author dparish
 */
@RunWith(JukitoRunner.class)
public class MainPresenterTest {
    @Inject
    MainPresenter mainPresenter;

    public static class MyModule extends JukitoModule {
        @Override
        protected void configureTest() {
        }

        @Provides
        @Singleton
        MainView mainView() {
            MainView view =  org.mockito.Mockito.mock(MainView.class);
            HasWidgets contentPanel = org.mockito.Mockito.mock(HasWidgets.class);
            org.mockito.Mockito.when(view.getContentPanel()).thenReturn(contentPanel);
            return view;
        }

    }
    @Test
    public void testMain(MainView view) {
        mainPresenter.onPageSelected(Page.CALENDAR);
        org.mockito.Mockito.verify(view).setPresenter(mainPresenter);
    }
}
