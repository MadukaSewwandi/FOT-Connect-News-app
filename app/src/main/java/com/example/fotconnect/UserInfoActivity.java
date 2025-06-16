package com.example.fotconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.*;
import androidx.annotation.NonNull;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

public class UserInfoActivity extends Activity {

    private TextView usernameTextView, emailTextView;
    private Button editInfoButton, signOutButton, backButton;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference dbRef;

    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        editInfoButton = findViewById(R.id.edit_info_button);
        signOutButton = findViewById(R.id.sign_out_button);
        backButton = findViewById(R.id.back_button);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("usernames");

        if (currentUser == null) {
            startActivity(new Intent(UserInfoActivity.this, SignInActivity.class));
            finish();
            return;
        }

        // Load user data from database
        dbRef.orderByValue().equalTo(currentUser.getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            currentUsername = child.getKey();
                            usernameTextView.setText("Username: " + currentUsername);
                            emailTextView.setText("Email: " + currentUser.getEmail());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UserInfoActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        editInfoButton.setOnClickListener(v -> showEditDialog());
        signOutButton.setOnClickListener(v -> showSignOutDialog());

        // ✅ Back button navigation to Sport News Activity
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, SportNewsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout dialogLayout = (LinearLayout) inflater.inflate(R.layout.activity_edit_user, null);
        builder.setView(dialogLayout);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText editUsername = dialogLayout.findViewById(R.id.editUsername);
        EditText editEmail = dialogLayout.findViewById(R.id.editEmail);
        Button okButton = dialogLayout.findViewById(R.id.ok_button);
        Button cancelButton = dialogLayout.findViewById(R.id.cancel_button);

        editUsername.setText(currentUsername);
        editEmail.setText(currentUser.getEmail());

        okButton.setOnClickListener(v -> {
            String newUsername = editUsername.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();

            if (newUsername.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            currentUser.updateEmail(newEmail).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (currentUsername != null) {
                        dbRef.child(currentUsername).removeValue();
                    }
                    dbRef.child(newUsername).setValue(newEmail).addOnCompleteListener(task2 -> {
                        if (task2.isSuccessful()) {
                            Toast.makeText(this, "Username & Email updated", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else {
                            Toast.makeText(this, "Database update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Email update failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());
    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout dialogLayout = (LinearLayout) inflater.inflate(R.layout.activity_sign_out_dialog, null);
        builder.setView(dialogLayout);
        AlertDialog dialog = builder.create();
        dialog.show();

        Button okButton = dialogLayout.findViewById(R.id.ok_button);
        Button cancelButton = dialogLayout.findViewById(R.id.cancel_button);

        okButton.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserInfoActivity.this, SignInActivity.class));
            finish();
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());
    }
}
