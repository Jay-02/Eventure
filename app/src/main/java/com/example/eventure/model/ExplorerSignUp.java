package com.example.eventure.model;

import java.util.Date;

public class ExplorerSignUp {
    private String username;
    private String email;
    private String password, confirmPassword;
    // Gender: we need to pull that from the Spinner, but for now we assume its a string.
    private String gender;
    // Same as the above but we need to pull it from the datePicker
    Date birthdate;

    public ExplorerSignUp(String username, String password, String confirmPassword, String gender, Date birthdate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}