package com.example.eventure.view;

import android.app.DatePickerDialog;
import android.media.ImageReader;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventure.R;
import com.example.eventure.model.contract.ExplorerSignUpContract;
import com.example.eventure.presenter.ExplorerSignUpPresenter;

public class ExplorerSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ExplorerSignUpContract.View {
    private ExplorerSignUpPresenter presenter;
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
        EditText username = findViewById(R.id.explorer_signup_username);
//        SusernameText
        EditText email = findViewById(R.id.explorer_signup_email);
        EditText password = findViewById(R.id.explorer_signup_password);
        EditText repeatPassword = findViewById(R.id.explorer_signup_repeat_password);
        Button signUpButton = findViewById(R.id.explorer_signup_button);
        // Calendar button
        ImageButton calendarButton = findViewById(R.id.explorer_signup_birthdate_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePicker_Theme);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = String.valueOf(year)+"."+ String.valueOf(month)+"."+String.valueOf(day);

            }

        });
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        if (adapterView.getSelectedItem().toString().equals("Gender")) {
            showGenderError("Gender Not Selected!");
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
    public void showSignUpSuccess() {

    }

    @Override
    public void showSignUpFailure() {

    }
}

