package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SportNewsActivity extends Activity {
    ImageView profileIcon, developerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(SportNewsActivity.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(SportNewsActivity.this, DeveloperInfoActivity.class));
        });
    }
}

