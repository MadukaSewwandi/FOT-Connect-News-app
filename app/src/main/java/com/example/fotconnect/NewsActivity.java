package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NewsActivity extends Activity {
    ImageView profileIcon, developerIcon;
    LinearLayout navAcademics, navSports, navEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        navAcademics = findViewById(R.id.nav_academics);
        navSports = findViewById(R.id.nav_sports);
        navEvents = findViewById(R.id.nav_events);

        profileIcon.setOnClickListener(v ->
                startActivity(new Intent(NewsActivity.this, UserInfoActivity.class)));

        developerIcon.setOnClickListener(v ->
                startActivity(new Intent(NewsActivity.this, DeveloperInfoActivity.class)));

        // Navigate to Academics screen
        navAcademics.setOnClickListener(v ->
                startActivity(new Intent(NewsActivity.this, academic_news.class)));

        // You can add actions for Sports and Events if needed:
        navSports.setOnClickListener(v ->
                startActivity(new Intent(NewsActivity.this, NewsActivity.class)));

        navEvents.setOnClickListener(v ->
                startActivity(new Intent(NewsActivity.this, events_news.class)));
    }
}
