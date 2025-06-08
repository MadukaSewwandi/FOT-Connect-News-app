package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class academic_news extends Activity {
    ImageView profileIcon, developerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_news);

        profileIcon = findViewById(R.id.profile_icon);
        developerIcon = findViewById(R.id.dev_icon);

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(academic_news.this, UserInfoActivity.class));
        });

        developerIcon.setOnClickListener(v -> {
            startActivity(new Intent(academic_news.this, DeveloperInfoActivity.class));
        });
    }
}

