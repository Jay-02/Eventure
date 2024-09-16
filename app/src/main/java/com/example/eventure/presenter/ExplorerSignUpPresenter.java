package com.example.eventure.presenter;

import com.example.eventure.model.contract.ExplorerLoginContract;
import com.example.eventure.model.contract.ExplorerSignUpContract;

public class ExplorerSignUpPresenter implements ExplorerSignUpContract.Presenter {
    private ExplorerSignUpContract.View view;
    public ExplorerSignUpPresenter(ExplorerSignUpContract.View view){view = this.view;}
    @Override
    public void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {

    }

    @Override
    public void validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {
        boolean isValid = true;
        if(username == null || username.isEmpty() || username.length()<5){
            view.showUsernameError("Username not valid");
        }
//        if(email)
    }
}
