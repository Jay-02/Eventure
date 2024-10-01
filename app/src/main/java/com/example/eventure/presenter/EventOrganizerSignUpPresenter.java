package com.example.eventure.presenter;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.eventure.model.contract.EventOrganizerSignUpContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventOrganizerSignUpPresenter implements EventOrganizerSignUpContract.Presenter {
    EventOrganizerSignUpContract.View view;
    public  EventOrganizerSignUpPresenter(EventOrganizerSignUpContract.View view){
        this.view = view;
    }
    @Override
    public void handleExtraInformation(FirebaseAuth mAuth, FirebaseFirestore db, FirebaseUser user, String username, String contactNumber, String gid) {
        Map<String, Object> organizers = new HashMap<>();
        organizers.put("username", username);
        organizers.put("contactNumber",contactNumber );
        organizers.put("organizationGovernmentID", gid);
        db.collection("EventOrganizers").document(user.getUid())
                .set(organizers)
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

    @Override
    public boolean validateInput(String username, String email, String password, String rptPassword, String contactNumber, String gId) {
        boolean isValid = true;
        if (username.isEmpty()){
            isValid = false;
            view.showInformationError("Username Field is required");
        }
        if(email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid=false;
            view.showInformationError("Check email for errors");
        }
        if (password.isEmpty()){
            isValid = false;
            view.showInformationError("Password Field is required");
        }
        if (rptPassword.isEmpty()){
            isValid = false;
            view.showInformationError("Repeat Password Field is required");
        }
        if(contactNumber.isEmpty()){
            isValid=false;
            view.showInformationError("Enter a valid phone number that we can contact you through");
        }
        if (gId.isEmpty()){
            isValid = false;
            view.showInformationError("Enter your organization Government ID");
        }
        return isValid;
    }
}
