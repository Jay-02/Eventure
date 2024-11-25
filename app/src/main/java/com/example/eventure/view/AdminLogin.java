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
import com.example.eventure.model.contract.AdminLoginContract;
import com.example.eventure.presenter.AdminLoginPresenter;
import com.example.eventure.view.fragments.AdminHomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity implements AdminLoginContract.View {
    FirebaseAuth mAuth;
    AdminLoginContract.Presenter presenter;
    private EditText AdminUsernameInput;
    private EditText AdminPasswordInput;
    String username;
    String password;
    private Button AdminLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
        AdminUsernameInput =findViewById(R.id.admin_username_edit_text);
        AdminPasswordInput = findViewById(R.id.admin_password_edit_text);
        AdminLoginButton = findViewById(R.id.admin_login_button);
        mAuth = FirebaseAuth.getInstance();
        presenter = new AdminLoginPresenter(this);
        AdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = AdminUsernameInput.getText().toString();
                password = AdminPasswordInput.getText().toString();
                if(presenter.ValidateCredentials(username, password)){
                    signIn(username, password);
                }
            }
        });
    }

    @Override
    public void ShowErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signIn(String username, String paswword) {
        mAuth.signInWithEmailAndPassword(username, paswword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), AdminHomeFragment.class));
                }
                else {
                    ShowErrorMessage("Login Failed!");
                }
            }
        });
    }
}