package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOutDialogActivity extends Activity {

    Button okButton, cancelButton;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out_dialog);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);

        if (user == null) {
            startActivity(new Intent(SignOutDialogActivity.this, SignInActivity.class));
            finish();
        }

        okButton.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(SignOutDialogActivity.this, SignInActivity.class));
            finish(); // Close this screen
        });

        cancelButton.setOnClickListener(v -> {
            startActivity(new Intent(SignOutDialogActivity.this, UserInfoActivity.class));
            finish(); // Optional: close this screen
        });
    }
}
