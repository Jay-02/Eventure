package com.example.eventure.model.contract;

public interface EventOrganizerContract {
    interface View{
        void navigateToForgotPassword();
        void navigateToEventOrganizerHome();
        void showLoginError(String errorMessage);

    }
    interface Presenter{
        void handleLogin(String username, String password);
        void onDestroy();
    }
}
