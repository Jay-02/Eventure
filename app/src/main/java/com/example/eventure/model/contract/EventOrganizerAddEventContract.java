package com.example.eventure.model.contract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public interface EventOrganizerAddEventContract {
    interface View {
        void showEventNameError(String message);
        void showEventDescriptionError(String message);
        void showEventLocationError(String message);
        void showEventAgeRangeError(String message);
        void showEventGenderSpinnerError(String message);
        void showEventTimeError(String message);
        void showEventTicketPriceError(String message);
        void showEventDateError(String message);
        void showEventCategorySpinnerError(String message);
        void showEventDateDialog();
        void showDocAddSuccess(String message);
        void showDocAddFailure(String message);


    }
    interface Presenter{
        boolean validateInput(String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate,String EventCategories, String AttendeeGender );
        void handleEventInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user,String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate, String EventCategories, String AttendeeGender);
    }
}
