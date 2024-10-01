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
import com.example.eventure.presenter.EventOrganizerSignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventOrganizerSignUp extends AppCompatActivity implements EventOrganizerSignUpContract.View {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EventOrganizerSignUpPresenter presenter;
    private EditText contactNumberInput;
    private EditText gidInput;
    private EditText usernameInput;
    private EditText rptPasswordInput;
    private FirebaseFirestore db;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser = null;
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
        usernameInput = findViewById(R.id.event_organizer_signup_username_input);
        contactNumberInput = findViewById(R.id.event_organizer_signup_number_input);
        presenter = new EventOrganizerSignUpPresenter(this);
        gidInput = findViewById(R.id.event_organizer_signup_gid_input);
        rptPasswordInput = findViewById(R.id.event_organizer_signup_rpt_password_input);
        Button signUpButton = findViewById(R.id.event_organizer_signup_button);

        db = FirebaseFirestore.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String rptPassword = rptPasswordInput.getText().toString();
                String username = usernameInput.getText().toString();
                String contactNumber = contactNumberInput.getText().toString();
                String gid = gidInput.getText().toString();
                if (presenter.validateInput(username, email, password, rptPassword, contactNumber, gid )) {

                    signUp(username, email, password, contactNumber, gid);
                }

            }
        });
    }

    @Override
    public void signUp(String username, String email, String password, String contactNumber, String gId) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = mAuth.getCurrentUser();
                    // Sign in success, update UI with the signed-in user's information
                    assert user != null;
                    presenter.handleExtraInformation(mAuth, db, user, username, contactNumber, gId);
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

    @Override
    public void showDocAddSuccess(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDocAddFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInformationError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}