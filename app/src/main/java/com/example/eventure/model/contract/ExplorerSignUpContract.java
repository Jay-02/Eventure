package com.example.eventure.model.contract;

public interface ExplorerSignUpContract {
    interface View {
        void showGenderError(String message);

        void showUsernameError(String message);

        void emailNotValid();

        void emailAlreadyUsed();

        void passwordNotValid();

        void passwordMismatch();


        void birthdateNotValid();

        void SignUpSuccess();

        void SignUpFailure();



    }

    interface Presenter {
        void onSignUpClicked(String username, String email, String password, String reEnterPassword, String gender, String birthdate);
        void validateInput(String username, String email, String password, String reEnterPassword, String gender, String birthdate);

    }
}
