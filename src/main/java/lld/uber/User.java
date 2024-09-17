package lld.uber;

import java.util.UUID;

public class User {
    protected String userId;
    protected String firstName;
    protected String lastName;

    public User(String firstName, String lastName) {
        this.userId = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }
}
