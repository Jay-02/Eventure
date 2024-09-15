package com.example.eventure.view;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.adapters.AdapterViewBindingAdapter;

import com.example.eventure.R;
import com.example.eventure.model.contract.ExplorerSignUpContract;

public class ExplorerSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ExplorerSignUpContract.View {
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
                TypedValue.COMPLEX_UNIT_DIP, -100, getResources().getDisplayMetrics());
        spinner.setDropDownVerticalOffset((int) pixels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        if (adapterView.getSelectedItem().toString().equals("Gender")) {
            showGenderError("Gender Not Selected!");
        }

    }
        @Override
        public void onNothingSelected (AdapterView < ? > adapterView){
            showGenderError("Gender Not Selected!");
        }

    @Override
    public void showGenderError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        //TODO: Might Change the outline of the field to red
    }

    @Override
    public void usernameNotAvailable() {

    }

    @Override
    public void emailNotValid() {

    }

    @Override
    public void emailAlreadyUsed() {

    }

    @Override
    public void passwordNotValid() {

    }

    @Override
    public void passwordMismatch() {

    }

    @Override
    public void birthdateNotValid() {

    }

    @Override
    public void SignUpSuccess() {

    }

    @Override
    public void SignUpFailure() {

    }
}

