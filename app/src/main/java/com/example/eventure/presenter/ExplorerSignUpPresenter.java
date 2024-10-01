package com.example.eventure.presenter;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.eventure.model.contract.ExplorerSignUpContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExplorerSignUpPresenter implements ExplorerSignUpContract.Presenter {
    private ExplorerSignUpContract.View view;
    public ExplorerSignUpPresenter(ExplorerSignUpContract.View view){this.view = view;}
    @Override
    public void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {
        validateInput(username,email,password,reEnterPassword,gender,birthdate);
    }

    @Override
    public boolean validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate) {
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

        return isValid;
    }

    @Override
    public void handleExtraInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user, String username, String gender, String birthdate) {
        Map<String, Object> organizers = new HashMap<>();
        organizers.put("username", username);
        organizers.put("birthdate",birthdate );
        organizers.put("gender", gender);
        db.collection("Explorers").document(user.getUid())
                .set(organizers)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        view.showFirebaseSuccess("User Info Added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.showFirebaseFailure("Error Occurred" + e);
                    }
                });
    }
}
