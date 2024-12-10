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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize views
        noResultsText = findViewById(R.id.no_results_text);
        sortSpinner = findViewById(R.id.sort_spinner);

        // Setup spinner
        setupSortSpinner();

        // Initialize events list and RecyclerView
        initializeEventsList();
        setupRecyclerView();

        // Setup search functionality
        setupSearchView();
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

    private void initializeEventsList() {
        allEventsList = new ArrayList<>();
        // Add events with the same data as in HomeFragment
        allEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_1,
                R.string.event_location_1, R.string.event_date_1, R.string.event_desc_1));
        allEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_2,
                R.string.event_location_2, R.string.event_date_2, R.string.event_desc_2));
        allEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_3,
                R.string.event_location_3, R.string.event_date_3, R.string.event_desc_3));
        allEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_4,
                R.string.event_location_4, R.string.event_date_4, R.string.event_desc_4));
        allEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_5,
                R.string.event_location_5, R.string.event_date_5, R.string.event_desc_5));
    }

    private void filterAndSortEvents(String text) {
        ArrayList<Event> filteredList = new ArrayList<>();

        // Filter events
        for (Event event : allEventsList) {
            String eventName = getString(event.getEventNameID()).toLowerCase();
            String eventLocation = getString(event.getLocationID()).toLowerCase();
            String eventDate = getString(event.getDateID()).toLowerCase();

            if (eventName.contains(text.toLowerCase()) ||
                    eventLocation.contains(text.toLowerCase()) ||
                    eventDate.contains(text.toLowerCase())) {
                filteredList.add(event);
            }
        }

        // Sort events based on selected option
        int sortOption = sortSpinner.getSelectedItemPosition();
        sortEvents(filteredList, sortOption);

        updateUI(filteredList);
    }

    private void sortEvents(ArrayList<Event> events, int sortOption) {
        switch (sortOption) {
            case 0: // Date (newest first)
                Collections.sort(events, (e1, e2) ->
                        getString(e2.getDateID()).compareTo(getString(e1.getDateID())));
                break;
            case 1: // Date (oldest first)
                Collections.sort(events, (e1, e2) ->
                        getString(e1.getDateID()).compareTo(getString(e2.getDateID())));
                break;
            case 2: // Name (A-Z)
                Collections.sort(events, (e1, e2) ->
                        getString(e1.getEventNameID()).compareTo(getString(e2.getEventNameID())));
                break;
            case 3: // Name (Z-A)
                Collections.sort(events, (e1, e2) ->
                        getString(e2.getEventNameID()).compareTo(getString(e1.getEventNameID())));
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
}