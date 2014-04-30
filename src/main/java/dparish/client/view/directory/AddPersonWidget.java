package dparish.client.view.directory;

import javax.swing.text.html.HTML;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import dparish.shared.models.Person;

/**
 * Created by david.parish on 4/29/14.
 */
public class AddPersonWidget extends Composite implements AddPersonView {
    interface AddPersonWidgetUiBinder extends UiBinder<HTMLPanel, AddPersonWidget> {
    }

    private static AddPersonWidgetUiBinder ourUiBinder = GWT.create(AddPersonWidgetUiBinder.class);

    @UiField
    TextBox firstName;

    @UiField
    TextBox lastName;

    @UiField
    TextBox phoneNumber;

    @UiField
    Button add;

    private Presenter presenter;

    @Inject
    public AddPersonWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void addSucceeded() {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
    }

    @Override
    public void addFailed(String message) {
        Window.alert(message);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("add")
    public void onAddClicked(ClickEvent evt) {
        presenter.personAdded(getPerson());
    }

    private Person getPerson() {
        Person person = new Person ();
        person.setFirstName(firstName.getText());
        person.setLastName(lastName.getText());
        person.setPhone(phoneNumber.getText());
        return person;
    }
}