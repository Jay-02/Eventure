package com.example.eventure.model.contract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public interface EventOrganizerAddEventContract {
    interface View {
        void showErrorMessage(String message);
        void showEventDateDialog();
        void showDocAddSuccess(String message);
        void showDocAddFailure(String message);
        void navigateToDashboard();

    }
    interface Presenter{
        boolean validateInput(String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate,String EventCategories, String AttendeeGender );
        void handleEventInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user,String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate, String EventCategories, String AttendeeGender);
    }
}
