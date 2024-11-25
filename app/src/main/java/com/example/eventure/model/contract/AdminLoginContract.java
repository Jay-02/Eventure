package com.example.eventure.model.contract;

public interface AdminLoginContract {
    interface View{
        void ShowErrorMessage(String message);
        void signIn(String username, String paswword);
    }
    interface Presenter{
        boolean ValidateCredentials(String username, String password);
    }
}
