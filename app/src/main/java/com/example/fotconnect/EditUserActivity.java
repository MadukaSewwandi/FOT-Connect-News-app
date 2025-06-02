package com.example.fotconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class EditUserActivity extends Activity {

    EditText editUsername, editEmail;
    Button okButton, cancelButton;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference dbRef;

    String currentUsername = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // UI elements
        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Firebase init
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("usernames");

        if (currentUser == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Step 1: Find current username from DB
        dbRef.orderByValue().equalTo(currentUser.getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            currentUsername = child.getKey();
                            editUsername.setText(currentUsername);
                            editEmail.setText(currentUser.getEmail());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditUserActivity.this, "Error loading user data", Toast.LENGTH_SHORT).show();
                    }
                });

        okButton.setOnClickListener(v -> {
            String newUsername = editUsername.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();

            if (newUsername.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Step 2: Update email in Firebase Auth
            currentUser.updateEmail(newEmail).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    // Step 3: Update Realtime Database - remove old username
                    if (currentUsername != null) {
                        dbRef.child(currentUsername).removeValue();
                    }

                    // Step 4: Set new username → new email
                    dbRef.child(newUsername).setValue(newEmail).addOnCompleteListener(task2 -> {
                        if (task2.isSuccessful()) {
                            Toast.makeText(this, "Username and email updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, UserInfoActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Database update failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(this, "Email update failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        cancelButton.setOnClickListener(v -> {
            startActivity(new Intent(EditUserActivity.this, UserInfoActivity.class));
            finish();
        });
    }
}
