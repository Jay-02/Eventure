package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.example.eventure.R;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class layout extends AppCompatActivity {

    private ImageView backgroundImage;
    private TextView eventTitle;
    private TextView eventDate;
    private TextView eventTime;
    private TextView eventLocation;
    private TextView eventAgeRange;
    private TextView eventPrice;
    private Button cancelBookingButton;
    private Button callButton;
    private ImageView backButton;
    ArrayList<String> stringArray = new ArrayList<>();
    private FirebaseFirestore db;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        backgroundImage = findViewById(R.id.background_image);
        eventTitle = findViewById(R.id.event_title);
        eventDate = findViewById(R.id.event_date);
        eventTime = findViewById(R.id.event_time);
        eventLocation = findViewById(R.id.event_location);
        eventAgeRange = findViewById(R.id.event_age_range);
        eventPrice = findViewById(R.id.event_price);
        cancelBookingButton = findViewById(R.id.cancelBookingButton);
        callButton = findViewById(R.id.call);
        backButton = findViewById(R.id.imageView2);
        stringArray = new ArrayList<>();
        stringArray = getIntent().getStringArrayListExtra("stringArrayKey");
        db = FirebaseFirestore.getInstance();
        Log.w("Lojain", String.valueOf(stringArray));
        loadEventData();
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cancelBookingButton.setOnClickListener(v -> {
            // Handle cancel booking

            // Ensure stringArray is not null before performing any operations
            if (stringArray != null && stringArray.contains(id)) {
                // Remove the event ID from the array
                stringArray.remove(id);

                // Update the Firestore with the updated list
                Map<String, Object> updateData = Map.of("booked_events", stringArray);
                db.collection("Explorers")
                        .document(userUid)
                        .set(updateData, SetOptions.merge())  // Use merge() to update the field without overwriting others
                        .addOnSuccessListener(aVoid -> {
                            cancelBookingButton.setText("Book now");
                            // Optionally show a success message
                        })
                        .addOnFailureListener(e -> {
                            System.err.println("Error removing value: " + e.getMessage());
                            // Optionally handle failure
                        });
            } else {
                System.out.println("Event ID not found in the list");
                // book now
                // Handle cancel booking
                // Ensure stringArray is not null before performing any operations
                if (stringArray != null && !stringArray.contains(id)) {
                    // Remove the event ID from the array
                    stringArray.add(id);

                    // Update the Firestore with the updated list
                    Map<String, Object> updateData = Map.of("booked_events", stringArray);
                    db.collection("Explorers")
                            .document(userUid)
                            .set(updateData, SetOptions.merge())  // Use merge() to update the field without overwriting others
                            .addOnSuccessListener(aVoid -> {
                                cancelBookingButton.setText("cancel booking");
                                // Optionally show a success message
                            })
                            .addOnFailureListener(e -> {
                                System.err.println("Error removing value: " + e.getMessage());
                                // Optionally handle failure
                            });
                }}
            });


        callButton.setOnClickListener(v -> {

        });

        backButton.setOnClickListener(v -> finish());
    }

    private void loadEventData() {
        DocumentReference docRef = db.collection("events").document("event_id");
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    eventTitle.setText(document.getString("title"));
                    eventDate.setText(document.getString("date"));
                    eventTime.setText(document.getString("time"));
                    eventLocation.setText(document.getString("location"));
                    eventAgeRange.setText(document.getString("ageRange"));
                    eventPrice.setText(document.getString("price"));
                    id = document.getString("id");
                    cancelBookingButton.setText("Book now");
                    if (stringArray != null) {
                        // Use the list as needed
                        for (String str : stringArray) {
                            if(str.equals(id)){
                                cancelBookingButton.setText("Cancel Booking");
                            }
                        }
                    }
                } else {



                }
            } else {



            }
        });
    }

    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), ExplorerLogin.class));

        }
    }


}