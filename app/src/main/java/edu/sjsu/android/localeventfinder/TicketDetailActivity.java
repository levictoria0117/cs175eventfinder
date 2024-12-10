package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
}