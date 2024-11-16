package com.example.eventure.presenter;

import androidx.annotation.NonNull;

import com.example.eventure.model.contract.EventOrganizerAddEventContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddEventFragmentPresenter implements EventOrganizerAddEventContract.Presenter {
    private final EventOrganizerAddEventContract.View view;
    public AddEventFragmentPresenter(EventOrganizerAddEventContract.View view){
        this.view = view;
    }
    private static final String EVENT_NAME_PATTERN = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)*$";
    private static final String EVENT_DESCRIPTION_PATTERN ="^(?:[A-Za-z0-9]+[.,!?]?(?:[-']?[A-Za-z0-9]+[.,!?]?)*\\s+){19}[A-Za-z0-9]+[.,!?]?(?:[-']?[A-Za-z0-9]+[.,!?]?)*.*$";
    @Override
    public boolean validateInput(String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate, String EventCategories, String AttendeeGender) {
        boolean isValid = true;
        if(!EventName.matches(EVENT_NAME_PATTERN)) {
            isValid = false;
            view.showEventNameError("Event Name is invalid!");
        }
        if(!EventDescription.matches(EVENT_DESCRIPTION_PATTERN)){
            isValid = false;
            view.showEventDescriptionError("Event Description Needs to be at least 20 words!");
        }
        if(AgeRange.equals("")){
            isValid = false;
            view.showEventAgeRangeError("Age is invalid");
        }
//        need to implement location error handling when adding google maps api
        if(Time.equals("")){
            view.showEventTimeError("You need to specify the Time of the Event");
            isValid = false;
        }
        if (EventTicketPrice.equals("")){
            view.showEventTicketPriceError("You need to specify A ticket price");
        }
        if(EventDate.equals("")){
            isValid = false;
            view.showEventDateError("Event date invalid");
        }
        if(EventCategories.equals(null)){
            isValid = false;
            view.showEventCategorySpinnerError("You need to specify the category of your event");
        }
        if (AttendeeGender.equals(null)){
            isValid = false;
            view.showEventGenderSpinnerError("You need to specify who your targeted audience is");
        }
        return isValid;
    }

    @Override
    public void handleEventInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user, String EventName, String EventDescription, String AgeRange, String Location, String Time, String EventTicketPrice, String EventDate, String EventCategories, String AttendeeGender) {
        Map<String, Object> event = new HashMap<>();
        event.put("eventName", EventName);
        event.put("eventDescription", EventDescription);
        event.put("ageRange", AgeRange);
        event.put("Location", Location);
        event.put("Time", Time);
        event.put("ticketPrice", EventTicketPrice);
        event.put("eventDate", EventDate);
        event.put("eventCategories", EventCategories);
        event.put("eventAttendeeGender",AttendeeGender);
        db.collection("PendingEvents").document(user.getUid())
                .set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        view.showDocAddSuccess("User Info Added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.showDocAddFailure("Error Occurred" + e);
                    }
                });
    }


}
