package com.example.eventure.view.fragments;

import static android.content.Intent.getIntent;

import com.example.eventure.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class EventDetailsActivity {
    private TextView tvEventName, tvEventDesc, tvEventSender, tvEventDate, tvEventTime, tvEventLocation, tvEventAge, tvEventPrice;
    private Button btnApprove, btnReject;

    private DatabaseReference eventsRef;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Initialize Firebase Database
        eventsRef = FirebaseDatabase.getInstance().getReference("Events");

        // Initialize UI Components
        tvEventName = findViewById(R.id.tv_event_name);
        tvEventDesc = findViewById(R.id.tv_event_desc);
        tvEventSender = findViewById(R.id.tv_event_sender);
        tvEventDate = findViewById(R.id.tv_event_date);
        tvEventTime = findViewById(R.id.tv_event_time);
        tvEventLocation = findViewById(R.id.tv_event_location);
        tvEventAge = findViewById(R.id.tv_event_age);
        tvEventPrice = findViewById(R.id.tv_event_price);
        btnApprove = findViewById(R.id.btn_approve);
        btnReject = findViewById(R.id.btn_reject);

        // Get event data from intent
        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        String eventName = intent.getStringExtra("eventName");
        String eventDesc = intent.getStringExtra("eventDesc");
        String eventSender = intent.getStringExtra("eventSender");
        String eventDate = intent.getStringExtra("eventDate");
        String eventTime = intent.getStringExtra("eventTime");
        String eventLocation = intent.getStringExtra("eventLocation");
        String eventAge = intent.getStringExtra("eventAge");
        String eventPrice = intent.getStringExtra("eventPrice");

        // Set the data to the views
        tvEventName.setText(eventName);
        tvEventDesc.setText(eventDesc);
        tvEventSender.setText(eventSender);
        tvEventDate.setText(eventDate);
        tvEventTime.setText(eventTime);
        tvEventLocation.setText(eventLocation);
        tvEventAge.setText(eventAge);
        tvEventPrice.setText(eventPrice);

        // Approve button logic
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventId != null) {
                    updateEventStatus(eventId, "approved");
                    Toast.makeText(EventDetailsActivity.this, "Event Approved", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                }
            }
        });

        // Reject button logic
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventId != null) {
                    updateEventStatus(eventId, "rejected");
                    Toast.makeText(EventDetailsActivity.this, "Event Rejected", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                }
            }
        });
    }
    private void updateEventStatus(String eventId, String status) {
        eventsRef.child(eventId).child("status").setValue(status);
    }
    Intent intent = getIntent();
    String eventId = intent.getStringExtra("eventId");
    String eventName = intent.getStringExtra("eventName");

}
