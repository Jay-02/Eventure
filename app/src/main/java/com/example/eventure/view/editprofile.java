package com.example.eventure.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.eventure.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class editprofile extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_REQUEST = 1;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReferenceFromUrl("gs://eventure-3583b.firebasestorage.app");    //change the url according to your firebase app
    int PICK_IMAGE_REQUEST=100;
    private Uri imageUri;
    private ImageView profileImage;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.updateinfo);
        ImageView back= findViewById(R.id.backButton);
        Button ChangePic=findViewById(R.id.changePictureButton);
        EditText username = findViewById(R.id.usernameField);
        EditText email = findViewById(R.id.emailField);
        EditText pass= findViewById(R.id.passwordField);
        Button updateDetails = findViewById(R.id.updateButton);
        Button logOut= findViewById(R.id.logoutButton);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        profileImage = findViewById(R.id.profileImage);


        back.setOnClickListener(view -> {
            Intent intent = new Intent(editprofile.this, ExplorerHome.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
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
            openFileChooser();
        });

        // Update user details
        updateDetails.setOnClickListener(v -> {
            String newUsername = username.getText().toString().trim();
            String newEmail = email.getText().toString().trim();
            String newPassword = pass.getText().toString().trim();

            if (newUsername.trim().isEmpty() || newEmail.trim().isEmpty() || newPassword.trim().isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                updateUserDetails(user, newEmail, newPassword, newUsername);
                finish();
                overridePendingTransition(0, 0);
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            }
        });

        // Logout user
        logOut.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(editprofile.this, ExplorerLogin.class));
            finish();
            overridePendingTransition(0, 0);
        });
    }

    private void openFileChooser() {
        // Check for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_REQUEST);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                    STORAGE_PERMISSION_REQUEST);
        }
        else {
            // Permission granted; open file chooser
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
        }
    }//end openFileChooser()


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST) {
                // Permission granted; open file chooser
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);

        }//end external if
    }//end onRequestPermissionsResult


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();


            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);            //getting image from gallery
                profileImage.setImageBitmap(bitmap);//Setting image to ImageView
                uploadImageToFirebase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//end onActivityResult



    private void uploadImageToFirebase() {
        if (imageUri != null) {

            String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference childRef = storageRef.child("Explorer_Profile/" + userUid + ".jpg");
            UploadTask uploadTask = childRef.putFile(imageUri);

            uploadTask.addOnSuccessListener(taskSnapshot -> {

                childRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveImageUrlToFirestore(userUid, imageUrl);
                });
            }).addOnFailureListener(e -> {
                // Handle failure
                Toast.makeText(this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "No file selected!", Toast.LENGTH_SHORT).show();
        }
    }//end upload image to storage


    private void saveImageUrlToFirestore(String userUid, String imageUrl) {
        // Get Firestore instance
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        // Create a document with the UID as its name
        Map<String, Object> userData = new HashMap<>();
        userData.put("imageUrl", imageUrl);

        firestore.collection("Explorers").document(userUid).update(userData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Image URL saved successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to save URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private void updateUserDetails(FirebaseUser user, String newEmail, String newPassword, String newUsername) {
        user.updateEmail(newEmail).addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
            user.updatePassword(newPassword).addOnSuccessListener(aVoid1 -> {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("username", newUsername);

                db.collection("Explorers").document(user.getUid())
                        .update(userMap)
                        .addOnSuccessListener(aVoid2 -> Toast.makeText(this, "updated successfully ", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show());
            }).addOnFailureListener(e -> Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show());
        }).addOnFailureListener(e -> Toast.makeText(this, "Failed to update email", Toast.LENGTH_SHORT).show());
    }

    public void signOut(MenuItem item) {
        if (item.getItemId() == R.id.explorer_logout_bottom_nav_button) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), ExplorerLogin.class));

        }

    }}


