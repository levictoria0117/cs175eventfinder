package edu.sjsu.android.localeventfinder;

import android.database.Cursor;
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

        return view;
    }
}
