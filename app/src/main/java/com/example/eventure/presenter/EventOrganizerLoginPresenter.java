package com.example.eventure.presenter;

import com.example.eventure.model.contract.EventOrganizerLoginContract;

public class EventOrganizerLoginPresenter implements EventOrganizerLoginContract.Presenter {
    private final EventOrganizerLoginContract.View view;

    public EventOrganizerLoginPresenter(EventOrganizerLoginContract.View view) {
        this.view = view;
    }

    @Override
    public boolean handleLogin(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            view.showLoginError("Enter Username");
            isValid = false;
        }
        if (password.isEmpty()) {
            view.showLoginError("Enter Password");
            isValid = false;
        }
        return isValid;


    }

    @Override
    public void onDestroy() {

    }
}
