package com.example.eventure.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventure.R;
import com.example.eventure.model.contract.EventOrganizerContract;
import com.example.eventure.presenter.EventOrganizerPresenter;

public class EventOrganizerLogin extends AppCompatActivity implements EventOrganizerContract.View {
    private EventOrganizerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EventOrganizerPresenter(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_organizer_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button loginButton = findViewById(R.id.event_organizer_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToEventOrganizerHome();

            }
        });
        TextView forgetPasswordButton = findViewById(R.id.event_organizer_login_forgot_password);
        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToForgotPassword();
            }
        });

    }

    @Override
    public void navigateToForgotPassword() {
        startActivity(new Intent(EventOrganizerLogin.this, ForgotPassword.class));
    }

    @Override
    public void navigateToEventOrganizerHome() {
        startActivity(new Intent(this, EventOrganizerHomeScreen.class));
    }

    @Override
    public void showLoginError(String errorMessage) {

    }
}