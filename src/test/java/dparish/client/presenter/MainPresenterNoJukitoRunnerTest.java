package dparish.client.presenter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jukito.SandboxTestModule;
import dparish.client.view.MainView;
import dparish.client.view.Page;
import org.junit.Test;

/**
 * @author dparish
 */
public class MainPresenterNoJukitoRunnerTest {

    Injector injector = Guice.createInjector(new SandboxTestModule());

    @Test
    public void testMain() {
        MainView view = injector.getInstance(MainView.class);
        MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
        mainPresenter.onPageSelected(Page.CALENDAR);
        org.mockito.Mockito.verify(view).setPresenter(mainPresenter);
    }

}
