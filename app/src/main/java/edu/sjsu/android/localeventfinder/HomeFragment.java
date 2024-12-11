package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView nearestEventsRecyclerView, moreEventsRecyclerView;
    private MyAdapter nearestEventsAdapter, moreEventsAdapter;
    private ArrayList<Event> nearestEventsList, moreEventsList;
    private EventDatabaseHelper eventDatabaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the database helper
        eventDatabaseHelper = new EventDatabaseHelper(getContext());

        // Initialize the event lists
        nearestEventsList = new ArrayList<>();
        moreEventsList = new ArrayList<>();

        loadEventsFromDatabase();
    }

    private void loadEventsFromDatabase() {
        List<Event> allEvents = eventDatabaseHelper.getAllEvents();

        // Clear existing lists
        nearestEventsList.clear();
        moreEventsList.clear();

        // Split events into nearest and more events
        if (allEvents.size() > 0) {
            int splitIndex = Math.min(5, allEvents.size());
            nearestEventsList.addAll(allEvents.subList(0, splitIndex));
            if (allEvents.size() > splitIndex) {
                moreEventsList.addAll(allEvents.subList(splitIndex, allEvents.size()));
            }
        }

        // Notify adapters if they're initialized
        if (nearestEventsAdapter != null) {
            nearestEventsAdapter.notifyDataSetChanged();
        }
        if (moreEventsAdapter != null) {
            moreEventsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerViews and Adapters
        moreEventsRecyclerView = view.findViewById(R.id.more_events_recycler);
        moreEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moreEventsAdapter = new MyAdapter(moreEventsList, false);
        moreEventsRecyclerView.setAdapter(moreEventsAdapter);

        nearestEventsRecyclerView = view.findViewById(R.id.nearest_events_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false);
        nearestEventsRecyclerView.setLayoutManager(gridLayoutManager);
        nearestEventsAdapter = new MyAdapter(nearestEventsList, true);
        nearestEventsRecyclerView.setAdapter(nearestEventsAdapter);

        moreEventsAdapter.setOnEventCardClickedListener(position -> {
            Event event = moreEventsList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        nearestEventsAdapter.setOnEventCardClickedListener(position -> {
            Event event = nearestEventsList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        ImageView searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        TextView seeAllButton = view.findViewById(R.id.see_all);
        seeAllButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadEventsFromDatabase();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (eventDatabaseHelper != null) {
            eventDatabaseHelper.close();
        }
    }
}

