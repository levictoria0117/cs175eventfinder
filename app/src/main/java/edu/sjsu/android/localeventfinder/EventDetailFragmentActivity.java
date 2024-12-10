package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetailFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        event = intent.getParcelableExtra("event");

        if (event != null) {
            ImageView eventImage = findViewById(R.id.event_image);
            TextView eventName = findViewById(R.id.event_name);
            TextView eventLocation = findViewById(R.id.event_location);
            TextView eventDate = findViewById(R.id.event_date);
            TextView eventDescription = findViewById(R.id.event_description);

            eventImage.setImageResource(event.getImageID());
            eventName.setText(event.getEventNameID());
            eventLocation.setText(event.getLocationID());
            eventDate.setText(event.getDateID());
            eventDescription.setText(event.getDescriptionID());
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Assuming the event has latitude and longitude data
        double latitude = event.getLatitude(); // Example: 37.7749
        double longitude = event.getLongitude(); // Example: -122.4194

        LatLng eventLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(eventLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 15)); // Zoom level of the map
    }
}


