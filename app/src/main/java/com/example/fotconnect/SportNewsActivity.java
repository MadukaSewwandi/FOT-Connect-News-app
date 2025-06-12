package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SportNewsActivity extends Activity {
    ImageView profileIcon, developerIcon;
    LinearLayout navAcademics, navSports, navEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        navAcademics = findViewById(R.id.nav_academics);
        navSports = findViewById(R.id.nav_sports);
        navEvents = findViewById(R.id.nav_events);

        profileIcon.setOnClickListener(v ->
                startActivity(new Intent(SportNewsActivity.this, UserInfoActivity.class)));

        developerIcon.setOnClickListener(v ->
                startActivity(new Intent(SportNewsActivity.this, DeveloperInfoActivity.class)));

        navAcademics.setOnClickListener(v ->
                startActivity(new Intent(SportNewsActivity.this, AcademicNewsActivity.class)));

        navSports.setOnClickListener(v ->
                startActivity(new Intent(SportNewsActivity.this, SportNewsActivity.class)));

        navEvents.setOnClickListener(v ->
                startActivity(new Intent(SportNewsActivity.this, EventsNewsActivity.class)));
    }
}
