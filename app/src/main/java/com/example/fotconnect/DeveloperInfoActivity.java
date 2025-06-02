package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DeveloperInfoActivity extends Activity {
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        exitButton = findViewById(R.id.exit_button);

        exitButton.setOnClickListener(v -> {
            startActivity(new Intent(DeveloperInfoActivity.this, NewsActivity.class));
        });
    }
}

