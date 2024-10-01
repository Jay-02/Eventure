package com.example.eventure.model.contract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public interface EventOrganizerSignUpContract {
    interface View{
        void signUp(String username, String email, String password, String contactNumber, String gId);
        void navigateToOrganizerHome();
        void showDocAddSuccess(String message);
        void showDocAddFailure(String message);
        void showInformationError(String message);
    }
    interface Presenter{
        void handleExtraInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user,String username, String contactNumber, String gid);
        boolean validateInput(String username, String email, String password, String rptPassword, String contactNumber, String gId);
    }

}
