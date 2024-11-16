package com.example.eventure.view.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eventure.R;
import com.example.eventure.model.contract.EventOrganizerAddEventContract;
import com.example.eventure.presenter.AddEventFragmentPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventOrganizerAddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventOrganizerAddEventFragment extends Fragment implements EventOrganizerAddEventContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    EventOrganizerAddEventContract.Presenter presenter;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    private EventOrganizerAddEventContract.View contractView;
    private Button UplaodImageButton;
    private Button EventDateButton;
    private Spinner EventGenderSpinner;
    private String eventGender;
    private Spinner EventCategorySpinner;
    private String eventCategory;
    private EditText EventNameInput;
    private EditText EventDescriptionInput;
    private EditText EventLocationInput;
    private EditText EventTimeInput;
    private EditText EventTicketPriceInput;
    private Button SubmitEventButton;
    private String EventName;
    private String EventDescription;
    private String EventLocation;
    private String EventTime;
    private String EventTicketPrice;
    private EditText EventAgeRangeInput;
    private String EventAgeRange;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String date;


    public EventOrganizerAddEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventOrganizerAddEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventOrganizerAddEventFragment newInstance() {
        EventOrganizerAddEventFragment fragment = new EventOrganizerAddEventFragment();
        Bundle args = new Bundle();

//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            currentUser = null;
//        }
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventOrganizerLoginPresenter presenter = new EventOrganizerLoginPresenter(contractView);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }
    // The error is because an onclicklistener is added
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_event_organizer_add_event, container ,false);
        InitializeViews(view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.new_event_gender, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, -120, getResources().getDisplayMetrics());
        EventGenderSpinner.setDropDownVerticalOffset((int) pixels);
        EventGenderSpinner.setAdapter(adapter);
        EventGenderSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.new_event_categories, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        EventCategorySpinner.setDropDownVerticalOffset((int) pixels);
        EventCategorySpinner.setAdapter(categoryAdapter);
        EventCategorySpinner.setOnItemSelectedListener(this);
        EventDateButton.setOnClickListener(this);
        SubmitEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInfo();
                presenter.handleEventInformation(mAuth,db, user,EventName, EventDescription, EventAgeRange, EventLocation, EventTime, EventTicketPrice, date, eventCategory, eventGender );
            }
        });
        return view;
    }
    private void getInfo(){
    EventName = EventNameInput.getText().toString();
    EventDescription=EventDescriptionInput.getText().toString();
    EventLocation = EventLocationInput.getText().toString();
    EventTime= EventTimeInput.getText().toString();
    EventTicketPrice = EventTicketPriceInput.getText().toString();
    EventAgeRange = EventAgeRangeInput.getText().toString();
//    user = mAuth.getCurrentUser();
    }
    private void InitializeViews(View view){
        EventCategorySpinner = view.findViewById(R.id.new_event_category_spinner);
        EventDateButton = view.findViewById(R.id.new_event_date_button);
        EventGenderSpinner = view.findViewById(R.id.new_event_gender_spinner);
        EventNameInput = view.findViewById(R.id.add_event_name_input);
        EventDescriptionInput = view.findViewById(R.id.add_event_name_description_input);
        EventLocationInput = view.findViewById(R.id.add_event_location_input);
        EventTicketPriceInput = view.findViewById(R.id.new_event_ticket_price);
        EventTimeInput = view.findViewById(R.id.new_event_time_input);
        EventAgeRangeInput = view.findViewById(R.id.add_event_age_input);
        SubmitEventButton = view.findViewById(R.id.new_event_submit_button);
        UplaodImageButton = view.findViewById(R.id.new_event_image_button);
        presenter = new AddEventFragmentPresenter(contractView);

    }
    @Override
    public void showEventNameError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventDescriptionError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventLocationError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventAgeRangeError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventGenderSpinnerError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventTimeError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventTicketPriceError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showEventDateError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventCategorySpinnerError(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEventDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireView().getContext(), R.style.EventOrganizer_DatePicker_Theme);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = day + "/" + (month + 1) + "/" + year;
                Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
//                if (Calendar.getInstance().get(Calendar.YEAR) - year < 18) {
//                    birthdateNotValid("You are too young to sign up!");
//                }
            }

        });
        datePickerDialog.show();
    }

    @Override
    public void showDocAddSuccess(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDocAddFailure(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        showEventDateDialog();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        eventGender = adapterView.getSelectedItem().toString();
        eventCategory = adapterView.getSelectedItem().toString();
        Toast.makeText(view.getContext(), adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if(adapterView.getSelectedItem() == null){
            showEventGenderSpinnerError("You have to select the gender of attendees");
        }

    }
}



