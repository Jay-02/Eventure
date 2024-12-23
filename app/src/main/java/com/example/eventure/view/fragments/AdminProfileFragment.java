package com.example.eventure.view.fragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.eventure.R;
import com.example.eventure.view.AdminLogin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AdminProfileFragment extends Fragment {
    private EditText usernameEditText, emailEditText, passwordEditText;
    private Button updateButton, logoutButton;

    private View view;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    //private DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout
         view=inflater.inflate(R.layout.fragment_admin_profile, container, false);
        // Initialize Firebase
        /*auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Admins");*/


        // Initialize Views
            usernameEditText = view.findViewById(R.id.admin_profile_username);
            emailEditText = view.findViewById(R.id.admin_profile_email);
            passwordEditText = view.findViewById(R.id.admin_profile_password);
            updateButton = view.findViewById(R.id.admin_profile_update_button);
            logoutButton = view.findViewById(R.id.admin_profile_logout_button);
        // Load Admin Data
        loadAdminData();

        // Handle Update Button
        updateButton.setOnClickListener(v -> updateAdminProfile());

        // Handle Logout Button
        logoutButton.setOnClickListener(v -> logoutAdmin());

        return view;
    }
    private void loadAdminData() {
        if (currentUser != null) {
            usernameEditText.setText(currentUser.getDisplayName());
            emailEditText.setText(currentUser.getEmail());
        }
    }
    private void updateAdminProfile() {
        String newUsername = usernameEditText.getText().toString().trim();
        String newEmail = emailEditText.getText().toString().trim();
        String newPassword = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(newUsername) || TextUtils.isEmpty(newEmail)) {
            Toast.makeText(getContext(), "Username and Email cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update email
        currentUser.updateEmail(newEmail).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Email updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to update email!", Toast.LENGTH_SHORT).show();
            }
        });

        // Update password
        if (!TextUtils.isEmpty(newPassword)) {
            currentUser.updatePassword(newPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Password updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update password!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    private void logoutAdmin() {
        auth.signOut();
        startActivity(new Intent(getContext(), AdminLogin.class));
        getActivity().finish();
    }

}