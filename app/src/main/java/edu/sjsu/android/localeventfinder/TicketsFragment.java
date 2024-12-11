package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class TicketsFragment extends Fragment {

    private RecyclerView ticketsRecyclerView;
    private TicketAdapter ticketsAdapter;
    private ArrayList<Event> ticketList;
    private EventDatabaseHelper eventDatabaseHelper;
    private TextView noTicketsText;

    public TicketsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database helper
        eventDatabaseHelper = new EventDatabaseHelper(getContext());

        // Initialize ticket list
        ticketList = new ArrayList<>();

        // Load registered events from database
        loadRegisteredEvents();
    }

    private void loadRegisteredEvents() {
        // Query database for events where registered is true
        SQLiteDatabase db = eventDatabaseHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM events WHERE registered = true";

        Cursor cursor = db.rawQuery(selectQuery, null);
        ticketList.clear();

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int imageUrlIndex = cursor.getColumnIndexOrThrow("image_url");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int locationIndex = cursor.getColumnIndexOrThrow("location");
            int dateIndex = cursor.getColumnIndexOrThrow("date");
            int descriptionIndex = cursor.getColumnIndexOrThrow("description");
            int latitudeIndex = cursor.getColumnIndexOrThrow("latitude");
            int longitudeIndex = cursor.getColumnIndexOrThrow("longitude");
            int favoriteIndex = cursor.getColumnIndexOrThrow("favorite");

            do {
                Event event = new Event(
                        cursor.getString(imageUrlIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(locationIndex),
                        cursor.getString(dateIndex),
                        cursor.getString(descriptionIndex),
                        cursor.getDouble(latitudeIndex),
                        cursor.getDouble(longitudeIndex),
                        cursor.getInt(favoriteIndex) == 1,
                        true  // We know it's registered since we queried for registered=1
                );
                event.setId(cursor.getLong(idIndex));
                ticketList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Notify adapter if it's initialized
        if (ticketsAdapter != null) {
            ticketsAdapter.notifyDataSetChanged();
        }

        // Update empty state message if view is created
        updateEmptyState();

    }

    private void updateEmptyState() {
        if (noTicketsText != null) {
            if (ticketList.isEmpty()) {
                noTicketsText.setVisibility(View.VISIBLE);
                ticketsRecyclerView.setVisibility(View.GONE);
            } else {
                noTicketsText.setVisibility(View.GONE);
                ticketsRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        ticketsRecyclerView = view.findViewById(R.id.ticket_recycler_view);
        noTicketsText = view.findViewById(R.id.no_tickets_text);

        ticketsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketsAdapter = new TicketAdapter(ticketList);
        ticketsRecyclerView.setAdapter(ticketsAdapter);

        ticketsAdapter.setOnEventCardClickedListener(position -> {
            Event event = ticketList.get(position);
            Intent intent = new Intent(getContext(), TicketDetailActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload registered events when returning to this fragment
        loadRegisteredEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close database connection
        if (eventDatabaseHelper != null) {
            eventDatabaseHelper.close();
        }
    }
}