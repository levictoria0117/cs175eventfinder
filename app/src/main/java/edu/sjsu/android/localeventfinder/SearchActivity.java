package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter eventAdapter;
    private ArrayList<Event> allEventsList;
    private TextView noResultsText;
    private Spinner sortSpinner;
    private String currentQuery = "";
    private EventDatabaseHelper eventDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize database helper
        eventDatabaseHelper = new EventDatabaseHelper(this);

        // Initialize views
        noResultsText = findViewById(R.id.no_results_text);
        sortSpinner = findViewById(R.id.sort_spinner);

        // Setup spinner
        setupSortSpinner();

        // Initialize events list and RecyclerView
        allEventsList = new ArrayList<>();
        loadEventsFromDatabase();
        setupRecyclerView();

        // Setup search functionality
        setupSearchView();
    }

    private void loadEventsFromDatabase() {
        allEventsList.clear();
        allEventsList.addAll(eventDatabaseHelper.getAllEvents());
    }

    private void setupSortSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterAndSortEvents(currentQuery);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.search_results_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.VISIBLE);

        eventAdapter = new MyAdapter(allEventsList, false);
        recyclerView.setAdapter(eventAdapter);

        eventAdapter.setOnEventCardClickedListener(position -> {
            Event event = allEventsList.get(position);
            Intent intent = new Intent(this, EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });
    }

    private void setupSearchView() {
        SearchView searchBar = findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentQuery = query;
                filterAndSortEvents(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentQuery = newText;
                filterAndSortEvents(newText);
                return true;
            }
        });
    }

    private void filterAndSortEvents(String text) {
        ArrayList<Event> filteredList = new ArrayList<>();

        // Filter events
        for (Event event : allEventsList) {
            String eventName = event.getEventName().toLowerCase();
            String eventLocation = event.getLocation().toLowerCase();
            String eventDate = event.getDate().toLowerCase();

            if (eventName.contains(text.toLowerCase()) ||
                    eventLocation.contains(text.toLowerCase()) ||
                    eventDate.contains(text.toLowerCase())) {
                filteredList.add(event);
            }
        }

        // Sort events based on selected option
        int sortOption = sortSpinner.getSelectedItemPosition();
        sortEvents(filteredList, sortOption);

        // Update UI with filtered and sorted events
        updateUI(filteredList);
    }

    private void sortEvents(ArrayList<Event> events, int sortOption) {
        switch (sortOption) {
            case 0: // Date (newest first)
                Collections.sort(events, (e1, e2) -> e2.getDate().compareTo(e1.getDate()));
                break;
            case 1: // Date (oldest first)
                Collections.sort(events, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
                break;
            case 2: // Name (A-Z)
                Collections.sort(events, (e1, e2) -> e1.getEventName().compareTo(e2.getEventName()));
                break;
            case 3: // Name (Z-A)
                Collections.sort(events, (e1, e2) -> e2.getEventName().compareTo(e1.getEventName()));
                break;
        }
    }

    private void updateUI(ArrayList<Event> filteredList) {
        if (filteredList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noResultsText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noResultsText.setVisibility(View.GONE);

            eventAdapter = new MyAdapter(filteredList, false);
            recyclerView.setAdapter(eventAdapter);

            eventAdapter.setOnEventCardClickedListener(position -> {
                Event event = filteredList.get(position);
                Intent intent = new Intent(this, EventDetailFragmentActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload events when returning to this activity
        loadEventsFromDatabase();
        filterAndSortEvents(currentQuery);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close database connection
        if (eventDatabaseHelper != null) {
            eventDatabaseHelper.close();
        }
    }
}