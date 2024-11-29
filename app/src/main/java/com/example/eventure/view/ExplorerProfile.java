package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventure.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ExplorerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.updateinfo);
        ImageView back= findViewById(R.id.backButton);
        Button ChangePic=findViewById(R.id.changePictureButton);
        EditText username = findViewById(R.id.usernameField);
        EditText email = findViewById(R.id.emailField);
        EditText pass= findViewById(R.id.passwordField);
        Button updateDetails = findViewById(R.id.updateButton);
        Button logOut= findViewById(R.id.logoutButton);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

      //  BottomNavigationView navBar = findViewById(R.id.bottom_navigation_view);
        //navBar.setCheckedItem(R.id.profile);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(ExplorerProfile.this, ExplorerHome.class);
            startActivity(intent);
        });

        BottomNavigationView navBar = findViewById(R.id.bottom_navigation_view);
        navBar.setSelectedItemId(R.id.explorer_profile_bottom_nav_button);
        navBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.explorer_profile_bottom_nav_button) {
            }
            if (item.getItemId() == R.id.explorer_home_bottom_nav_button) {
                startActivity(new Intent(this, ExplorerHome.class));
                overridePendingTransition(0, 0);
            }
            if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
               signOut(item);

            }

            return false;
        });

        ChangePic.setOnClickListener(view -> {

        });

        updateDetails.setOnClickListener(view->{
            if (username.toString().isEmpty() || email.toString().isEmpty() || pass.toString().isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }else{
                FirebaseUser user = auth.getCurrentUser(); // Ensure user is authenticated

                user.updatePassword(pass.toString().trim());
                user.updateEmail(email.toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ExplorerProfile.this, " Email Updated Successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ExplorerProfile.this, " Email Updated Failure", Toast.LENGTH_SHORT).show();
                    }
                });


                // Create a map for the user data
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("username", username.toString());
                db.collection("EventOrganizers").document(user.getUid())
                        .update(userMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ExplorerProfile.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ExplorerProfile.this, "Update Failure", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(ExplorerProfile.this, ExplorerLogin.class);
                startActivity(intent);

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