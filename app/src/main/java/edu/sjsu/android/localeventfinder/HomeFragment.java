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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView nearestEventsRecyclerView, moreEventsRecyclerView;
    private MyAdapter nearestEventsAdapter, moreEventsAdapter;
    private ArrayList<Event> nearestEventsList, moreEventsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nearestEventsList = new ArrayList<>();
        nearestEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_1, R.string.event_location_1, R.string.event_date_1, R.string.event_desc_1));
        nearestEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_2, R.string.event_location_2, R.string.event_date_2, R.string.event_desc_2));
        nearestEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_3, R.string.event_location_3, R.string.event_date_3, R.string.event_desc_3));
        nearestEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_4, R.string.event_location_4, R.string.event_date_4, R.string.event_desc_4));
        nearestEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_5, R.string.event_location_5, R.string.event_date_5, R.string.event_desc_5));

        moreEventsList = new ArrayList<>();
        moreEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_1, R.string.event_location_1, R.string.event_date_1, R.string.event_desc_1));
        moreEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_2, R.string.event_location_2, R.string.event_date_2, R.string.event_desc_2));
        moreEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_3, R.string.event_location_3, R.string.event_date_3, R.string.event_desc_3));
        moreEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_4, R.string.event_location_4, R.string.event_date_4, R.string.event_desc_4));
        moreEventsList.add(new Event(R.drawable.event_temp_background, R.string.event_name_5, R.string.event_location_5, R.string.event_date_5, R.string.event_desc_5));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        moreEventsRecyclerView = view.findViewById(R.id.more_events_recycler);
        moreEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moreEventsAdapter = new MyAdapter(moreEventsList, false);
        moreEventsRecyclerView.setAdapter(moreEventsAdapter);

        nearestEventsRecyclerView = view.findViewById(R.id.nearest_events_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false);
        nearestEventsRecyclerView.setLayoutManager(gridLayoutManager);
        nearestEventsAdapter = new MyAdapter(nearestEventsList, true);
        nearestEventsRecyclerView.setAdapter(nearestEventsAdapter);

        // Set up click listeners for both adapters
        moreEventsAdapter.setOnEventCardClickedListener(position -> {
            Event event = moreEventsList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);  // Pass event data to the new Activity
            startActivity(intent);
        });

        nearestEventsAdapter.setOnEventCardClickedListener(position -> {
            Event event = nearestEventsList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);  // Pass event data to the new Activity
            startActivity(intent);
        });

        return view;
    }
}

