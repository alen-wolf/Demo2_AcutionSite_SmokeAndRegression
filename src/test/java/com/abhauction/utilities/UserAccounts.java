package com.abhauction.utilities;

import java.util.Random;

public class UserAccounts {

    private final String firstName = "Allen";
    private final String lastName = "Vukk";
    private String emailAndPassword;

    public UserAccounts(String emailAndPassword) {
        this.emailAndPassword = emailAndPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAndPassword() {
        return emailAndPassword;
    }

    public void setEmailAndPassword(String emailAndPassword) {
        this.emailAndPassword = emailAndPassword;
    }
}
