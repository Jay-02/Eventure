package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eventure.R;
import com.example.eventure.databinding.ActivityEventOrganizerHomeScreenBinding;
import com.example.eventure.view.fragments.EventOrganizerAddEventFragment;
import com.example.eventure.view.fragments.EventOrganizerDashboardFragment;
import com.example.eventure.view.fragments.EventOrganizerProfileFragment;
import com.google.firebase.auth.FirebaseAuth;

public class EventOrganizerHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventOrganizerHomeScreenBinding binding = ActivityEventOrganizerHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new EventOrganizerDashboardFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.add_event_bottom_nav_button) {
                replaceFragment(new EventOrganizerAddEventFragment());
            } else if (item.getItemId() == R.id.dashboard_bottom_nav_button) {
                replaceFragment(new EventOrganizerDashboardFragment());
            } else if (item.getItemId() == R.id.profile_bottom_nav_profile_button) {
                replaceFragment(new EventOrganizerProfileFragment());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottom_nav_frame, fragment);
        fragmentTransaction.commit();
    }
    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.event_organizer_logout_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), EventOrganizerLogin.class));

        }
    }
}