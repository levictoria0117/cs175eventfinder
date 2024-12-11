package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class EventDetailFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Event event;
    private EventDatabaseHelper eventDatabaseHelper;
    private MaterialButton registerButton;
    private ImageView favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventDatabaseHelper = new EventDatabaseHelper(this);

        Intent intent = getIntent();
        event = (Event) intent.getParcelableExtra("event");

        if (event != null) {
            ImageView eventImage = findViewById(R.id.event_image);
            TextView eventName = findViewById(R.id.event_name);
            TextView eventLocation = findViewById(R.id.event_location);
            TextView eventDate = findViewById(R.id.event_date);
            TextView eventDescription = findViewById(R.id.event_description);
            registerButton = findViewById(R.id.registerButton);
            favoriteButton = findViewById(R.id.favorite_button);

            // Load event image using Glide
            Glide.with(this)
                    .load(event.getImageUrl())
                    .into(eventImage);

            // Set event details
            eventName.setText(event.getEventName());
            eventLocation.setText(event.getLocation());
            eventDate.setText(event.getDate());
            eventDescription.setText(event.getDescription());

            // Update register button state
            updateRegisterButtonState();

            // Update favorite button state
            updateFavoriteButtonState();

            // Set up register button click listener
            registerButton.setOnClickListener(v -> {
                if (!event.isRegistered()) {
                    // Update database
                    eventDatabaseHelper.updateRegisteredStatus(event.getId(), true);
                    // Update local event object
                    event.setRegistered(true);
                    // Update UI
                    updateRegisterButtonState();
                    // Show confirmation
                    Toast.makeText(this, "Successfully registered for " + event.getEventName(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Set up favorite button click listener
            favoriteButton.setOnClickListener(v -> {
                boolean newFavoriteState = !event.isFavorite();
                // Update database
                eventDatabaseHelper.updateFavoriteStatus(event.getId(), newFavoriteState);
                // Update local event object
                event.setFavorite(newFavoriteState);
                // Update UI
                updateFavoriteButtonState();
                // Show confirmation
                String message = newFavoriteState ?
                        "Added to favorites" :
                        "Removed from favorites";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            });
        }

        // Set up the back button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Initialize and set up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void updateRegisterButtonState() {
        if (event.isRegistered()) {
            registerButton.setText("Registered");
            registerButton.setEnabled(false);
            registerButton.setBackgroundTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.gray)));  // Add a gray color to your colors.xml
        } else {
            registerButton.setText("Register");
            registerButton.setEnabled(true);
            registerButton.setBackgroundTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.primary)));
        }
    }

    private void updateFavoriteButtonState() {
        if (event.isFavorite()) {
            favoriteButton.setImageTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.primary)));
        } else {
            favoriteButton.setImageTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, android.R.color.darker_gray)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventDatabaseHelper != null) {
            eventDatabaseHelper.close();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (event != null) {
            double latitude = event.getLatitude();
            double longitude = event.getLongitude();

            LatLng eventLocation = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(eventLocation).title(event.getEventName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 15));
        }
    }
}





