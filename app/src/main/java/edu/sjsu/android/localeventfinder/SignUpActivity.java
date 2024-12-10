package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;
    private TextInputEditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize UI elements
        firstNameEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.first_name)).getEditText();
        lastNameEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.last_name)).getEditText();
        emailEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.email)).getEditText();
        passwordEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.password)).getEditText();

        userDatabaseHelper = new UserDatabaseHelper(this);

        Button signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(view -> handleSignUp());

        Button signInButton = findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        Button guestButton = findViewById(R.id.guest_button);
        guestButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void handleSignUp() {
        // Get user inputs
        String fName = firstNameEditText != null ? firstNameEditText.getText().toString().trim() : "";
        String lName = lastNameEditText != null ? lastNameEditText.getText().toString().trim() : "";
        String email = emailEditText != null ? emailEditText.getText().toString().trim() : "";
        String password = passwordEditText != null ? passwordEditText.getText().toString().trim() : "";

        // Validate inputs
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password are required to sign up.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email format. Use a valid email (e.g., name@domain.com).", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password must be at least 8 characters long and include uppercase, lowercase, a number, and a special character.", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if the email is already registered
        if (userDatabaseHelper.checkEmail(email)) {
            Toast.makeText(this, "This email is already registered. Please log in.", Toast.LENGTH_SHORT).show();
        } else {
            // Insert user into the database
            boolean isInserted = userDatabaseHelper.insertData(email, password, fName, lName);
            if (isInserted) {
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sign Up Failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Email validation
    private boolean isValidEmail(String email) {
        return email.contains("@") && (email.endsWith(".com") || email.endsWith(".edu"));
    }

    // Password validation
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}
