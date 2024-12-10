package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class TicketDetailActivity extends AppCompatActivity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        MaterialButton seeDetailButton = findViewById(R.id.seeDetailButton);
        seeDetailButton.setOnClickListener(v -> {
            Intent intent = new Intent(TicketDetailActivity.this, EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        MaterialButton getDirectionButton = findViewById(R.id.getDirectionButton);
        getDirectionButton.setOnClickListener(v -> openGoogleMaps());

        Intent intent = getIntent();
        event = intent.getParcelableExtra("event");

        if (event != null) {
            ImageView eventImage = findViewById(R.id.eventImageView);
            TextView eventName = findViewById(R.id.eventNameTextView);
            TextView eventLocation = findViewById(R.id.venueTextView);
            TextView eventTicketOrderId = findViewById(R.id.orderIdTextView);
            TextView userName = findViewById(R.id.nameTextView);
            TextView eventDate = findViewById(R.id.dateTextView);
            TextView eventTime = findViewById(R.id.timeTextView);

            eventImage.setImageResource(event.getImageID());
            eventName.setText(event.getEventNameID());
            eventLocation.setText(event.getLocationID());
            eventTicketOrderId.setText("#78492");
            userName.setText("John Doe");
            eventDate.setText(event.getDateID());
            eventTime.setText(event.getDateID());
        }
    }

    private void openGoogleMaps() {
        if (event != null) {
            try {
                String location = "1 Washington Square, San Jose, CA 95192";

                // Create a URI for Google Maps with the location
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode(location));

                // Create an Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                // Verify that Google Maps is installed
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // If Google Maps isn't installed, open in browser
                    Uri browserUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(location));
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                    startActivity(browserIntent);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Unable to open maps", Toast.LENGTH_SHORT).show();
            }
        }
    }

}