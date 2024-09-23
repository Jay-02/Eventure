package com.example.eventure.model.contract;

public interface ExplorerLoginContract {
    interface View {
        void showToast(String message);
        void signIn(String email, String password);
        void showLoginError(String message);
        void navigateToExplorerHome();
        void navigateToExplorerSignUp();
        void navigateToForgotPassword();

    }
    interface Presenter{
        boolean validateLoginCredentials(String username, String password);
        void onDestroy();

    }

}
