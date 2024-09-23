package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eventure.R;
import com.example.eventure.databinding.ActivityExplorerHomeBinding;
import com.example.eventure.view.fragments.EventOrganizerDashboardFragment;
import com.example.eventure.view.fragments.EventOrganizerProfileFragment;
import com.example.eventure.view.fragments.ExplorerHomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ExplorerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorer_home);
        ActivityExplorerHomeBinding binding = ActivityExplorerHomeBinding.inflate(getLayoutInflater());
        replaceFragment(new ExplorerHomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.explorer_home_bottom_nav_button) {
                replaceFragment(new ExplorerHomeFragment());
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
        fragmentTransaction.replace(R.id.explorer_bottom_nav_frame, fragment);
        fragmentTransaction.commit();
    }

    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Explorer_Login.class));

        }
    }
}