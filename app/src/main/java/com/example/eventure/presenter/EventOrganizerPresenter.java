package com.example.eventure.presenter;

import com.example.eventure.model.contract.EventOrganizerContract;

public class EventOrganizerPresenter implements EventOrganizerContract.Presenter {
    private final EventOrganizerContract.View view;

    public EventOrganizerPresenter(EventOrganizerContract.View view){
        this.view = view;
    }

    @Override
    public void handleLogin(String email, String password) {
        if (email.equals("admin") && password.equals("admin")) {
            view.navigateToEventOrganizerHome();  // On successful login, navigate to home
        } else {
            view.showLoginError("Invalid username or password");
        }
    }

    @Override
    public void onDestroy() {

    }
}
