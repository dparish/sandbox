package dparish.shared.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by david.parish on 4/29/14.
 */
public class Person implements IsSerializable{

    private String firstName;
    private String lastName;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
