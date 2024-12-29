package com.example.eventure.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eventure.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_xml);



        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ImageView profileImage = findViewById(R.id.profileImage);
        DocumentReference userRef = db.collection("Explorers").document(userId);


        if (currentUser == null) {
            Intent intent = new Intent(ProfileActivity.this, ExplorerLogin.class);
            startActivity(intent);
            finish();
        }


        userRef.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String imageUrl = documentSnapshot.getString("imageUrl");
                        Picasso.get().load(imageUrl).into(profileImage);



                    }
                });

        Button editProfile = findViewById(R.id.changePictureButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, editprofile.class);
                startActivity(intent);
            }
        });

        LinearLayout intrests = findViewById(R.id.intrests_layout);
        intrests.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, choose.class);
            startActivity(intent);
        });

    }
    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), ExplorerLogin.class));

        }
    }
}
