package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                // User is already signed in
                startActivity(new Intent(SplashActivity.this, SportNewsActivity.class));
            } else {

                //User not signed in
                startActivity(new Intent(SplashActivity.this, SignInActivity.class));
            }

            finish();
        }, 2000); // 2-second delay
    }
}
