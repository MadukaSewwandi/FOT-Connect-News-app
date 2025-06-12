package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AcademicNewsActivity extends Activity {
    ImageView profileIcon, developerIcon;
    LinearLayout navAcademics, navSports, navEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        navAcademics = findViewById(R.id.nav_academics);
        navSports = findViewById(R.id.nav_sports);
        navEvents = findViewById(R.id.nav_events);

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, DeveloperInfoActivity.class));
        });

        navAcademics.setOnClickListener(v -> {
            // Already on Academic screen, you can ignore or refresh
            // startActivity(new Intent(AcademicNewsActivity.this, AcademicNewsActivity.class));
        });

        navSports.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, SportNewsActivity.class));
            finish();
        });

        navEvents.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, EventsNewsActivity.class));
            finish();
        });
    }
}
