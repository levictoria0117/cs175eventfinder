package edu.sjsu.android.localeventfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TicketsFragment extends Fragment {

    private RecyclerView ticketsRecyclerView;
    private TicketAdapter ticketsAdapter;
    private ArrayList<Event> ticketList;

    public TicketsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketList = new ArrayList<>();

        ticketList.add(new Event("https://example.com/event_image_1.jpg", "Event Name 5", "Event Location 5", "2024-12-09", "Event Description 5", 37.7749, -122.4194));
        ticketList.add(new Event("https://example.com/event_image_2.jpg", "Event Name 2", "Event Location 2", "2024-12-10", "Event Description 2", 34.0522, -118.2437));
        ticketList.add(new Event("https://example.com/event_image_3.jpg", "Event Name 3", "Event Location 3", "2024-12-11", "Event Description 3", 40.7128, -74.0060));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        ticketsRecyclerView = view.findViewById(R.id.ticket_recycler_view);
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
}
