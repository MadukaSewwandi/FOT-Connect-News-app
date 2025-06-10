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

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(AcademicNewsActivity.this, DeveloperInfoActivity.class));
        });

        // Navigate to Academics screen
        navAcademics.setOnClickListener(v ->
                startActivity(new Intent(AcademicNewsActivity.this, AcademicNewsActivity.class)));

        // You can add actions for Sports and Events if needed:
        navSports.setOnClickListener(v ->
                startActivity(new Intent(AcademicNewsActivity.this, NewsActivity.class)));

        navEvents.setOnClickListener(v ->
                startActivity(new Intent(AcademicNewsActivity.this, EventsNewsActivity.class)));
    }
}

