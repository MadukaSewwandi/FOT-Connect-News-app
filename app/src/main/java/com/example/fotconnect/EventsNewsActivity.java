package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EventsNewsActivity extends Activity {
    ImageView profileIcon, developerIcon;
    LinearLayout navAcademics, navSports, navEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, DeveloperInfoActivity.class));
        });

        // Navigate to Academics screen
        navAcademics.setOnClickListener(v ->
                startActivity(new Intent(EventsNewsActivity.this, AcademicNewsActivity.class)));

        // You can add actions for Sports and Events if needed:
        navSports.setOnClickListener(v ->
                startActivity(new Intent(EventsNewsActivity.this, SportNewsActivity.class)));


    }
}

