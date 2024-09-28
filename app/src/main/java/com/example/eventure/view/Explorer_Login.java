package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventure.R;
import com.example.eventure.model.contract.ExplorerLoginContract;
import com.example.eventure.presenter.ExplorerLoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Explorer_Login extends AppCompatActivity implements ExplorerLoginContract.View {
    private static final String TAG = "ExplorerLogin";
    private EditText emailInput;
    private EditText passwordInput;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    if(currentUser != null){
            navigateToExplorerHome();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorer_login);
        emailInput = findViewById(R.id.explorer_username_edit_text);
        passwordInput = findViewById(R.id.explorer_password_edit_text);

        Button loginButton = findViewById(R.id.explorer_login_button);
        TextView forgotPasswordButton = findViewById(R.id.explorer_login_forgot_password);
        TextView signUpButton = findViewById(R.id.explorer_login_create_an_account);
        ExplorerLoginContract.Presenter presenter = new ExplorerLoginPresenter(this);
        mAuth = FirebaseAuth.getInstance();
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToForgotPassword();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(presenter.validateLoginCredentials(email, password)) signIn(email, password);
                else {
                    showLoginError("Check email and password!");
                }

            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToExplorerSignUp();
            }
        });

    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), ExplorerHome.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            showLoginError("Wrong Email or password");

                        }
                    }
                });
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToExplorerHome() {
        startActivity(new Intent(getApplicationContext(), ExplorerHome.class));
    }

    @Override
    public void navigateToExplorerSignUp() {
         startActivity(new Intent(this, ExplorerSignUp.class));
    }

    @Override
    public void navigateToForgotPassword() {
        startActivity(new Intent(this, ForgotPassword.class));
    }


}

