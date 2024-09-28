package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventure.R;
import com.example.eventure.model.contract.EventOrganizerSignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EventOrganizerSignUp extends AppCompatActivity implements EventOrganizerSignUpContract.View {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToOrganizerHome();
      }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_organizer_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText emailInput = findViewById(R.id.event_organizer_signup_email_input);
        EditText passwordInput = findViewById(R.id.event_organizer_signup_password_input);
        Button signUpButton = findViewById(R.id.event_organizer_signup_button);
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(email, password);
            }
        });
    }

    @Override
    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    navigateToOrganizerHome();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void navigateToOrganizerHome() {
        startActivity(new Intent(getApplicationContext(), EventOrganizerHomeScreen.class));
    }
}