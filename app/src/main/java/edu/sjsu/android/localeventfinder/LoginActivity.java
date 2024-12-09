package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;
    private TextInputEditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the database helper
        userDatabaseHelper = new UserDatabaseHelper(this);

        // Initialize input fields
        emailEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.email)).getEditText();
        passwordEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.password)).getEditText();

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
        String email = emailEditText != null ? emailEditText.getText().toString().trim() : "";
        String password = passwordEditText != null ? passwordEditText.getText().toString().trim() : "";

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isAuthenticated = userDatabaseHelper.checkEmailPassword(email, password);
        if (isAuthenticated) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
