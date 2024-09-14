package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventure.R;
import com.example.eventure.model.contract.ExplorerLoginContract;
import com.example.eventure.presenter.LoginPresenter;

public class Explorer_Login extends AppCompatActivity implements ExplorerLoginContract.View {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private TextView forgotPasswordButton;
    private TextView signUpButton;
    private ExplorerLoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorer_login);
        emailInput = findViewById(R.id.explorer_username_edit_text);
        passwordInput = findViewById(R.id.explorer_password_edit_text);
        loginButton = findViewById(R.id.explorer_login_button);
        forgotPasswordButton = findViewById(R.id.explorer_login_forgot_password);
        signUpButton = findViewById(R.id.explorer_login_create_an_account);
        presenter = new LoginPresenter(this);
        loginButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.handleLogin(emailInput.getText().toString(), passwordInput.getText().toString());
            }
        });
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToForgotPassword();
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
    public void showToast() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToExplorerHome() {
        startActivity(new Intent(this, ExplorerHome.class));
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

