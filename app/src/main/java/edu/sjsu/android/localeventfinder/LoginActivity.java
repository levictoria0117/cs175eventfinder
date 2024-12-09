package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;
    private TextInputEditText emailEditText, passwordEditText;
    private CheckBox keepSignedInCheckbox;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Check if the user is already signed in
        boolean isSignedIn = sharedPreferences.getBoolean("isSignedIn", false);
        if (isSignedIn) {
            navigateToMainActivity();
        }

        // Initialize the database helper
        userDatabaseHelper = new UserDatabaseHelper(this);

        // Initialize input fields and checkbox
        emailEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.email)).getEditText();
        passwordEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.password)).getEditText();
        keepSignedInCheckbox = findViewById(R.id.keep_signed_in_checkbox);

        // Set up buttons
        Button signIn = findViewById(R.id.signin_button);
        Button signUp = findViewById(R.id.button_sign_in);
        Button guest = findViewById(R.id.guest_button);

        signIn.setOnClickListener(v -> handleLogin());

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        guest.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        if (emailEditText == null || passwordEditText == null) {
            Toast.makeText(this, "Error initializing input fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            boolean isAuthenticated = userDatabaseHelper.checkEmailPassword(email, password);
            if (isAuthenticated) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Save "Keep Me Signed In" preference
                if (keepSignedInCheckbox.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isSignedIn", true);
                    editor.apply();
                }

                navigateToMainActivity();
            } else {
                Toast.makeText(this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred during login. Please try again.", Toast.LENGTH_SHORT).show();
            Log.e("LoginActivity", "Authentication error", e);
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Prevent going back to LoginActivity
    }
}
