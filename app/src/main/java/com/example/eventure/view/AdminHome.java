package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eventure.view.fragments.AdminProfileFragment;
import com.example.eventure.R;
import com.example.eventure.databinding.ActivityAdminHomeBinding;
import com.example.eventure.view.fragments.AdminDashboardFragment;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);
        ActivityAdminHomeBinding binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        replaceFragment(new AdminDashboardFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.admin_dashboard_bottom_nav_button) {
                replaceFragment(new AdminDashboardFragment());
            } else if (item.getItemId() == R.id.admin_bottom_nav_profile_button) {
                replaceFragment(new AdminProfileFragment());
            }
            return true;

        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.admin_bottom_nav_frame, fragment);
        fragmentTransaction.commit();
    }

    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.admin_bottom_nav_logout_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), AdminLogin.class));

        }
    }
}

