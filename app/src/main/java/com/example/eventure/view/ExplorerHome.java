package com.example.eventure.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eventure.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ExplorerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorer_home);

      //  ActivityExplorerHomeBinding binding = ActivityExplorerHomeBinding.inflate(getLayoutInflater());
      //  replaceFragment(new ExplorerHomeFragment());
      //  binding.bottomNavigationView.setOnItemSelectedListener(item -> {
      //      if (item.getItemId() == R.id.explorer_home_bottom_nav_button) {
      //          replaceFragment(new ExplorerHomeFragment());
      //      } else if (item.getItemId() == R.id.dashboard_bottom_nav_button) {
      //          replaceFragment(new EventOrganizerDashboardFragment());
      //      } else if (item.getItemId() == R.id.profile_bottom_nav_profile_button) {
      //          Log.w("profile","new profile activity");
      //          startActivity(new Intent(this, ExplorerProfile.class));
      //          overridePendingTransition(0, 0);            }
      //      return true;
      //  });
        BottomNavigationView navBar = findViewById(R.id.bottom_navigation_view);
        navBar.setSelectedItemId(R.id.explorer_home_bottom_nav_button);
        navBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.explorer_profile_bottom_nav_button) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startActivity(new Intent(this, ProfileActivity.class));
                }
                overridePendingTransition(0, 0);
            }
            if (item.getItemId() == R.id.explorer_home_bottom_nav_button) {

            }
            if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
                FirebaseAuth auth= FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(this, ExplorerLogin.class));
                overridePendingTransition(0, 0);
                finish();

            }

            return false;
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
            startActivity(new Intent(getApplicationContext(), ExplorerLogin.class));

        }
    }
}