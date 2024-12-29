package com.example.eventure.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eventure.InterestAdapter;
import com.example.eventure.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class interestEvent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InterestAdapter interestAdapter;
    private List<com.example.eventure.view.InterestItem> interestList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_event);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

        interestAdapter = new InterestAdapter(interestList);
        recyclerView.setAdapter(interestAdapter);

        String interest = getIntent().getStringExtra("interest");

        if (interest != null) {
            addNewItem(interest);
        }
    }

    private void addNewItem(String interest) {
        int imageRes = getImageResource(interest);

        com.example.eventure.view.InterestItem newItem = new com.example.eventure.view.InterestItem(interest, imageRes);

        interestList.add(newItem);

        saveData();

        interestAdapter.notifyItemInserted(interestList.size() - 1);

        Toast.makeText(this, "Added: " + interest, Toast.LENGTH_SHORT).show();
    }

    private int getImageResource(String interest) {
        switch (interest.toLowerCase()) {
            case "music":
                return R.drawable.music;
            case "camping":
                return R.drawable.camping;
            case "art":
                return R.drawable.art;
            case "adventure":
                return R.drawable.adventure;
            case "sport":
                return R.drawable.sport;
            case "cooking":
                return R.drawable.cooking;
            default:
                return R.drawable.man;
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("InterestData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(interestList);
        editor.putString("interestList", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("InterestData", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("interestList", null);
        if (json != null) {
            interestList = gson.fromJson(json, new TypeToken<List<InterestItem>>(){}.getType());
        } else {
            interestList = new ArrayList<>();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearData();
    }

    private void clearData() {
        SharedPreferences sharedPreferences = getSharedPreferences("InterestData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("interestList");
        editor.apply();
}
}