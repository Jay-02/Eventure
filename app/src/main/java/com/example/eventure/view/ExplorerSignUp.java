package com.example.eventure.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventure.R;
import com.example.eventure.model.contract.ExplorerSignUpContract;
import com.example.eventure.presenter.ExplorerSignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class ExplorerSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ExplorerSignUpContract.View {
    EditText usernameInput;
    EditText repeatPasswordInput;
    EditText passwordInput;
    EditText emailInput;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser = null;
        }
    }
    private ExplorerSignUpPresenter presenter;
    private String gender;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_explorer_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.explorer_signup_gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_spinner, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, -120, getResources().getDisplayMetrics());
        spinner.setDropDownVerticalOffset((int) pixels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        usernameInput = findViewById(R.id.explorer_signup_username);
        emailInput = findViewById(R.id.explorer_signup_email);
        passwordInput = findViewById(R.id.explorer_signup_password);
        repeatPasswordInput = findViewById(R.id.explorer_signup_repeat_password);
        Button signUpButton = findViewById(R.id.explorer_signup_button);
        mAuth = FirebaseAuth.getInstance();
        // Calendar button
        ImageButton calendarButton = findViewById(R.id.explorer_signup_birthdate_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                    signUp(email, password);
//                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePicker_Theme);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = day + "/" + (month + 1) + "/" + year;
                Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
                if (Calendar.getInstance().get(Calendar.YEAR) - year < 18) {
                    birthdateNotValid("You are too young to sign up!");
                }
            }

        });
        datePickerDialog.show();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        if (adapterView.getSelectedItem().toString().equals("Gender")) {
            showGenderError("Gender Not Selected!");
        } else {
            gender = adapterView.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        showGenderError("Gender Not Selected!");
    }

    @Override
    public void showGenderError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        //TODO: Might Change the outline of the field to red
    }

    @Override
    public void showUsernameError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailNotValid(String errorMessage) {

    }


    @Override
    public void emailAlreadyUsed() {

    }

    @Override
    public void passwordNotValid(String errorMessage) {

    }

    @Override
    public void passwordMismatch(String errorMessage) {

    }

    @Override
    public void birthdateNotValid(String errorMessage) {

    }

    @Override
    public void showSignUpSuccess(String message) {
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignUpFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            navigateUpTo(new Intent(getApplicationContext(), ExplorerHome.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "something", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

