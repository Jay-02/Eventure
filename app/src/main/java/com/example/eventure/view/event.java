package com.example.eventure.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventure.R;

public class event {
        private String id;
        private String name;
        private String location;
        private String date;
        private String imageUrl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.event_list_item, container, false);

    }
        // Default constructor for Firebase
        public event() {}

        // Constructor
        public event(String id, String name, String location, String date, String imageUrl) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.date = date;
            this.imageUrl = imageUrl;
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

           /* "events": {
            "event1": {
            "id": "event1",
            "name": "Almond Book Club",
            "location": "Amman",
            "date": "19 May",
            "imageUrl": "https://example.com/image.jpg",
            "status": "Pending"
            }
            }
            }
            implementation 'com.github.bumptech.glide:glide:4.12.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
*/



