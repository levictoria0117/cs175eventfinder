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

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketList = new ArrayList<>();
        ticketList.add(new Event(R.drawable.event_temp_background, R.string.event_name_5, R.string.event_location_5, R.string.event_date_5, R.string.event_desc_5));
        ticketList.add(new Event(R.drawable.event_temp_background, R.string.event_name_2, R.string.event_location_2, R.string.event_date_2, R.string.event_desc_2));
        ticketList.add(new Event(R.drawable.event_temp_background, R.string.event_name_3, R.string.event_location_3, R.string.event_date_3, R.string.event_desc_3));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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