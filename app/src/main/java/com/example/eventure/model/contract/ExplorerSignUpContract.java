package com.example.eventure.model.contract;

public interface ExplorerSignUpContract {
    interface View {
        void showGenderError(String message);

        void showUsernameError(String message);

        void emailNotValid(String errorMessage);

        void emailAlreadyUsed();

        void passwordNotValid(String errorMessage);

        void passwordMismatch(String errorMessage);


        void birthdateNotValid(String errorMessage);

        void showSignUpSuccess();

        void showSignUpFailure();



    }

    interface Presenter {
        void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate);
        void validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate);

    }
}
