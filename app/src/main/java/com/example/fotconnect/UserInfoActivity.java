package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class UserInfoActivity extends Activity {
    Button editInfoButton, backButton, signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        editInfoButton = findViewById(R.id.edit_info_button);
        backButton = findViewById(R.id.back_button);
        signOutButton = findViewById(R.id.sign_out_button);

        editInfoButton.setOnClickListener(v -> {
            startActivity(new Intent(UserInfoActivity.this, EditUserActivity.class));
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(UserInfoActivity.this, NewsActivity.class));
        });

        signOutButton.setOnClickListener(v -> {
            startActivity(new Intent(UserInfoActivity.this, SignOutDialogActivity.class));
        });
    }
}

