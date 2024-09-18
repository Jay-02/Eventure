package com.example.eventure.model.contract;

import android.widget.AdapterView;

public interface ExplorerLoginContract {
    interface View {
        void showToast();
        void showLoginError(String message);
        void navigateToExplorerHome();
        void navigateToExplorerSignUp();
        void navigateToForgotPassword();

    }
    interface Presenter{
        void handleLogin(String email, String password);
        void onDestroy();

    }

}
