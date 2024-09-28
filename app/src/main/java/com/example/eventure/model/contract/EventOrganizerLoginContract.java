package com.example.eventure.model.contract;

public interface EventOrganizerLoginContract {
    interface View{
        void navigateToForgotPassword();
        void navigateToEventOrganizerHome();
        void navigateToSignUp();
        void showLoginError(String errorMessage);
        void signIn(String email, String password);
    }
    interface Presenter{
        void handleLogin(String username, String password);
        void onDestroy();
    }
}
