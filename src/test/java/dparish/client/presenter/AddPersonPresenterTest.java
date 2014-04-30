package dparish.client.presenter;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.HasWidgets;
import dparish.client.resources.MainMessages;
import dparish.client.view.directory.AddPersonView;
import dparish.client.view.directory.DirectoryView;
import dparish.shared.models.Person;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by david.parish on 4/29/14.
 */
@RunWith(JukitoRunner.class)
public class AddPersonPresenterTest {

    @BeforeClass
    public static void setup() {
        GWTMockUtilities.disarm();
        GWTMockUtilities.returnMockMessages();
    }

    @AfterClass
    public static void tearDown() {
        GWTMockUtilities.restore();
    }

    @Test
    public void goTest(DirectoryPresenter directoryPresenter,
                       DirectoryView directoryView) {
        when(directoryView.getAddPersonContainer()).thenReturn(mock(HasWidgets.class));
        directoryPresenter.go(mock(HasWidgets.class));
    }


    @Test
    public void validationTests (AddPersonPresenter addPersonPresenter,
                                 AddPersonView addPersonView) {
        Person person = getPerson(null, "smith", "512 775 5942");
        addPersonPresenter.personAdded(person);
        verify(addPersonView).addFailed(MainMessages.INSTANCE.enterFirstName());
        person = getPerson("john", "smith", "512 775 abcd");
        addPersonPresenter.personAdded(person);
        verify(addPersonView).addFailed(MainMessages.INSTANCE.invalidPhoneFormat());
        person = getPerson("john", "smith", "512 775 5942");
        addPersonPresenter.personAdded(person);
        verify(addPersonView).addSucceeded();
    }

    private Person getPerson (String firstName, String lastName, String phone) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        return person;
    }
}

