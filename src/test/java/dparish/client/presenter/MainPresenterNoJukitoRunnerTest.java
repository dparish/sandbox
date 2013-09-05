package dparish.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dparish.client.resources.MainMessages;
import dparish.test.inject.SandboxTestModule;
import dparish.client.view.MainView;
import dparish.client.view.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author dparish
 */
public class MainPresenterNoJukitoRunnerTest {

    Injector injector = Guice.createInjector(new SandboxTestModule());

    @Before
    public void gwtNoBrowser() {
        GWTMockUtilities.disarm();
        GWTMockUtilities.returnMockMessages();
    }

    @After
    public void gwtReset() {
        GWTMockUtilities.restore();
    }

    @Test
    public void testMessages() {
        MainMessages messages = GWT.create(MainMessages.class);
        assertNotNull(messages);
    }

    @Test
    public void testMain() {
        MainView view = injector.getInstance(MainView.class);
        MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
        mainPresenter.onPageSelected(Page.CALENDAR);
        org.mockito.Mockito.verify(view).setPresenter(mainPresenter);
    }

}
