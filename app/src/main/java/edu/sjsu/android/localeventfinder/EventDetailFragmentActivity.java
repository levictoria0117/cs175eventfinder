package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
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

        Intent intent = getIntent();
        event = (Event) intent.getParcelableExtra("event");

        if (event != null) {
            ImageView eventImage = findViewById(R.id.event_image);
            TextView eventName = findViewById(R.id.event_name);
            TextView eventLocation = findViewById(R.id.event_location);
            TextView eventDate = findViewById(R.id.event_date);
            TextView eventDescription = findViewById(R.id.event_description);

            // Load event image using Glide
            Glide.with(this)
                    .load(event.getImageUrl())
                    .into(eventImage);

            // Set event details
            eventName.setText(event.getEventName());
            eventLocation.setText(event.getLocation());
            eventDate.setText(event.getDate());
            eventDescription.setText(event.getDescription());
        }

        // Initialize and set up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
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





