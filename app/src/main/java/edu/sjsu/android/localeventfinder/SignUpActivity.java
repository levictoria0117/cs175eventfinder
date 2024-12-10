package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;
    private TextInputEditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signIn = findViewById(R.id.button_sign_in);
        Button guest = findViewById(R.id.guest_button);

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        View.OnClickListener navigateToMain = v -> {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        };

        guest.setOnClickListener(navigateToMain);

        userDatabaseHelper = new UserDatabaseHelper(this);

        firstNameEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.first_name)).getEditText();
        lastNameEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.last_name)).getEditText();
        emailEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.email)).getEditText();
        passwordEditText = (TextInputEditText) ((TextInputLayout) findViewById(R.id.password)).getEditText();

        findViewById(R.id.signup_button).setOnClickListener(view -> handleSignUp());
    }

    private void handleSignUp() {
        String fName = firstNameEditText != null ? firstNameEditText.getText().toString().trim() : "";
        String lName = lastNameEditText != null ? lastNameEditText.getText().toString().trim() : "";
        String email = emailEditText != null ? emailEditText.getText().toString().trim() : "";
        String password = passwordEditText != null ? passwordEditText.getText().toString().trim() : "";
        String address = "Add Address";
        String phone = "Add Phone";

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password are required to sign up.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userDatabaseHelper.checkEmail(email)) {
            Toast.makeText(this, "This Email already has an account. Try Logging in.", Toast.LENGTH_LONG).show();
        } else {
            boolean isInserted = userDatabaseHelper.insertData(email, password, fName, lName, address, phone);
            if (isInserted) {
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sign Up Failed. Please try again.", Toast.LENGTH_LONG).show();
            }
        }

        Button signIn = findViewById(R.id.button_sign_in);

        View.OnClickListener navigateToSignin = v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        };

        signIn.setOnClickListener(navigateToSignin);
    }
}
