package com.example.eventure.presenter;

import android.util.Patterns;

import com.example.eventure.model.contract.ExplorerLoginContract;
import com.example.eventure.model.contract.ExplorerSignUpContract;
import com.example.eventure.model.database.DatabaseHelper;

import java.util.Objects;

public class ExplorerSignUpPresenter implements ExplorerSignUpContract.Presenter {
    private ExplorerSignUpContract.View view;
    public ExplorerSignUpPresenter(ExplorerSignUpContract.View view){view = this.view;}
    @Override
    public void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {
        validateInput(username,email,password,reEnterPassword,gender,birthdate);
    }

    @Override
    public void validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {
        boolean isValid = true;
        if(username == null || username.isEmpty() || username.length()<5){
            view.showUsernameError("Username not valid");
        }
        if(email == null ||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            view.emailNotValid("Email format invalid");
            isValid = false;
        }
        if (password ==null || password.length()<8){
            view.passwordNotValid("Password not valid");
            isValid = false;
        }
        if(reEnterPassword == null){
            view.passwordNotValid("Confirm your password");
            isValid=false;
        }
        if (!Objects.equals(reEnterPassword, password)){
            view.passwordMismatch("Passwords Do not match");
            isValid = false;
        }
        if (gender ==null || gender.isEmpty()){
            view.showGenderError("Choose your gender");
            isValid=false;
        }
        if (birthdate==null ||birthdate.isEmpty()){
            view.birthdateNotValid("Date not valid");
            isValid=false;
        }
        if (isValid){
            view.showSignUpSuccess();
        }
    }
}
