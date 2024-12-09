package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

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
