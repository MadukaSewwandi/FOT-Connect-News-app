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

        // Initialize top icons
        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        // Initialize bottom navigation views
        navAcademics = findViewById(R.id.nav_academics);
        navSports = findViewById(R.id.nav_sports);
        navEvents = findViewById(R.id.nav_events);

        // Top icon click listeners
        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, DeveloperInfoActivity.class));
        });

        // Bottom nav click listeners
        navAcademics.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, AcademicNewsActivity.class));
            finish(); // Optional: close current activity to avoid stacking
        });

        navSports.setOnClickListener(v -> {
            startActivity(new Intent(EventsNewsActivity.this, SportNewsActivity.class));
            finish();
        });

        navEvents.setOnClickListener(v -> {
            // Already on Events screen - you can ignore or refresh
            // startActivity(new Intent(EventsNewsActivity.this, EventsNewsActivity.class));
        });
    }
}
