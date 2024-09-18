package com.example.eventure.model;

public class Explorer {
    private String email;
    private String password;
    public Explorer(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    // this is supposed to pull or push information into the database
    // so these things are most likely going to change

}
