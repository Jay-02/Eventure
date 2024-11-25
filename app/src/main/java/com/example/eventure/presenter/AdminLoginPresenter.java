package com.example.eventure.presenter;

import com.example.eventure.model.contract.AdminLoginContract;

public class AdminLoginPresenter implements AdminLoginContract.Presenter{
    private final AdminLoginContract.View view;
    public AdminLoginPresenter(AdminLoginContract.View view){
       this.view = view;
    }

    @Override
    public boolean ValidateCredentials(String username, String password) {
        boolean isValid = true;
        if (username.isEmpty()){
            isValid = false;
            view.ShowErrorMessage("Please Enter Your Username");
        }
        if (password.isEmpty()){
            isValid = false;
            view.ShowErrorMessage("Please Enter Your Password");
        }
        return isValid;
    }
}
