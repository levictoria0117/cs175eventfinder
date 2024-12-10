package edu.sjsu.android.localeventfinder;


import android.database.Cursor;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import android.content.DialogInterface;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SettingsFragment extends Fragment {

    private String userEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString("USER_EMAIL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        EditText nameField = view.findViewById(R.id.name);
        EditText addressField = view.findViewById(R.id.address);
        EditText phoneField = view.findViewById(R.id.phone);
        Button saveButton = view.findViewById(R.id.save_button);

        UserDatabaseHelper db = new UserDatabaseHelper(getContext());
        Cursor cursor = db.getUserInfo(userEmail);

        if (cursor != null && cursor.moveToFirst()) {
            nameField.setText(cursor.getString(cursor.getColumnIndexOrThrow("fName")) + " " + cursor.getString(cursor.getColumnIndexOrThrow("lName")));
            addressField.setText(cursor.getString(cursor.getColumnIndexOrThrow("address")));
            phoneField.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
            cursor.close();
        }

        saveButton.setOnClickListener(v -> {
            String fullName = nameField.getText().toString().trim();
            String address = addressField.getText().toString().trim();
            String phone = phoneField.getText().toString().trim();

            boolean nameUpdated = db.updateUserName(userEmail, fullName);
            boolean addressUpdated = db.updateUserAddress(userEmail, address);
            boolean phoneUpdated = db.updateUserPhone(userEmail, phone);

            if (nameUpdated && addressUpdated && phoneUpdated) {
                Toast.makeText(getContext(), "Information updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to update some fields.", Toast.LENGTH_SHORT).show();
            }
        });
      
      // Initialize Logout button
        TextView logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> handleLogout());

        // Other buttons (Change Email, Change Password)
        TextView changeEmailButton = view.findViewById(R.id.change_email_button);
        changeEmailButton.setOnClickListener(v -> {
            // Logic for changing email
            // Placeholder: Add navigation or functionality here
        });

        TextView changePasswordButton = view.findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(v -> {
            // Logic for changing password
            // Placeholder: Add navigation or functionality here
        });

        return view;
    }

    private void handleLogout() {
        // Clear SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSignedIn", false);
        editor.apply();

        // Navigate to LoginActivity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

        // End the current activity (if necessary)
        requireActivity().finish();
    }
}
