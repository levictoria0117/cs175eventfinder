package edu.sjsu.android.localeventfinder;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.bumptech.glide.Glide;

public class EventDetailFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        event = getIntent().getParcelableExtra("event");

        if (event != null) {
            ImageView eventImage = findViewById(R.id.event_image);
            TextView eventName = findViewById(R.id.event_name);
            TextView eventLocation = findViewById(R.id.event_location);
            TextView eventDate = findViewById(R.id.event_date);
            TextView eventDescription = findViewById(R.id.event_description);

            Glide.with(this)
                    .load(event.getImageUrl())
                    .placeholder(R.drawable.event_temp_background)
                    .error(R.drawable.event_temp_background)
                    .into(eventImage);

            eventName.setText(event.getName());
            eventLocation.setText(event.getLocation());
            eventDate.setText(event.getDate());
            eventDescription.setText(event.getDescription());
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng eventLocation = new LatLng(event.getLatitude(), event.getLongitude());
        mMap.addMarker(new MarkerOptions().position(eventLocation).title(event.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 15));
    }
}






