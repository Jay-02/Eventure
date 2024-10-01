package com.example.eventure.model.contract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public interface ExplorerSignUpContract {
    interface View {
        void showGenderError(String message);

        void showUsernameError(String message);

        void emailNotValid(String errorMessage);

        void emailAlreadyUsed();

        void passwordNotValid(String errorMessage);

        void passwordMismatch(String errorMessage);


        void birthdateNotValid(String errorMessage);

        void showSignUpSuccess(String message);

        void showSignUpFailure(String message);

        void signUp(String email, String password);


        void showFirebaseSuccess(String userInfoAdded);

        void showFirebaseFailure(String FirebaseError);
        void navigateToExplorerHome();

    }

    interface Presenter {
        void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate);
        boolean validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate);
        void handleExtraInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user, String username, String gender, String birthdate);
    }
}
