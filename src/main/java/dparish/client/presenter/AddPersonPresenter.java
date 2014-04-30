package dparish.client.presenter;

import com.google.common.base.Strings;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.resources.MainMessages;
import dparish.client.view.directory.AddPersonView;
import dparish.shared.models.Person;

/**
 * Created by david.parish on 4/29/14.
 */
@Singleton
public class AddPersonPresenter extends BasePresenter<AddPersonView> implements AddPersonView.Presenter {

    @Inject
    public AddPersonPresenter(AddPersonView view) {
        super(view);
        view().setPresenter(this);
    }

    @Override
    public void personAdded(Person person) {
        if (Strings.isNullOrEmpty(person.getFirstName())) {
            view().addFailed(MainMessages.INSTANCE.enterFirstName());
            return;
        }
        if (Strings.isNullOrEmpty(person.getLastName())) {
            view().addFailed("Please enter a last name");
            return;
        }
        if (Strings.isNullOrEmpty(person.getPhone())) {
            view().addFailed("Please enter a phone number");
            return;
        }
        if (!validatePhone(person.getPhone())) {
            view().addFailed(MainMessages.INSTANCE.invalidPhoneFormat());
            return;
        }
        view().addSucceeded();
    }

    private boolean validatePhone(String phone) {
        String pattern = "(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}";
        RegExp phoneRegex = RegExp.compile(pattern);
        MatchResult matcher = phoneRegex.exec(phone);
        return matcher != null;
    }

}
