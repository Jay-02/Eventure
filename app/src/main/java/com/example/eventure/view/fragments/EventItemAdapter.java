package com.example.eventure.view.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eventure.R;
import com.google.firebase.auth.FirebaseAuth;

//import com.bumptech.glide.Glide;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
private FirebaseAuth mAuth;

public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.EventViewHolder> {

    private Context context;
    private List<event> eventList;

    public EventItemAdapter(Context context, List<event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.eventName.setText(event.getName());
        holder.eventLocationDate.setText(event.getLocation() + " - ON " + event.getDate());
        Glide.with(context).load(event.getImageUrl()).into(holder.eventImage);

        holder.acceptButton.setOnClickListener(v -> {
            mAuth eventRef = FirebaseDatabase.getInstance()
                    .getReference("events")
                    .child(event.getId());
            eventRef.child("status").setValue("Coming");
        });

        holder.declineButton.setOnClickListener(v -> {
            mAuth eventRef = FirebaseDatabase.getInstance()
                    .getReference("events")
                    .child(event.getId());
            eventRef.child("status").setValue("Declined");
        });

        // Set data to the views event item layout
        holder.tvEventName.setText(event.getName());
        holder.tvEventLocationDate.setText(event.getLocation() + " - " + event.getDate());

        // When More Details is clicked
        holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // Create an intent to navigate to EventDetailsActivity
                Intent intent = new Intent(context, EventDetailsActivity.class);

                // Pass event details to the new activity
                intent.putExtra("eventId", event.getId());
                intent.putExtra("eventName", event.getName());
                intent.putExtra("eventDesc", event.getDescription());
                intent.putExtra("eventSender", event.getSender());
                intent.putExtra("eventDate", event.getDate());
                intent.putExtra("eventTime", event.getTime());
                intent.putExtra("eventLocation", event.getLocation());
                intent.putExtra("eventAge", event.getAgeRange());
                intent.putExtra("eventPrice", event.getPrice());

                // Start the activity
                context.startActivity(intent);
            }
        });
        }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventName, eventLocationDate;
        ImageView eventImage;
        ImageButton acceptButton, declineButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.tv_event_name);
            eventLocationDate = itemView.findViewById(R.id.tv_event_location_date);
            eventImage = itemView.findViewById(R.id.iv_event_image);
            acceptButton = itemView.findViewById(R.id.btn_accept);
            declineButton = itemView.findViewById(R.id.btn_decline);
        }
    }

}
