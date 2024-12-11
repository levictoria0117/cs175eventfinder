package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
            TextView eventTicketOrderId = findViewById(R.id.ticket_orderId);
//            TextView userName = findViewById(R.id.nameTextView);
            TextView eventDate = findViewById(R.id.ticket_date);

            Glide.with(this)
                    .load(event.getImageUrl())
                    .into(eventImage);

            // Set text views with the event details
            eventName.setText(event.getEventName());
            eventLocation.setText(event.getLocation());
            eventTicketOrderId.setText("#" + String.format("%05d", (int)(Math.random() * 100000)));
//            userName.setText("John Doe");
            eventDate.setText(event.getDate());
        }
    }

    private void openGoogleMaps() {
        if (event != null) {
            try {
                String location = event.getLatitude() + "," + event.getLongitude();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode(location));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
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
