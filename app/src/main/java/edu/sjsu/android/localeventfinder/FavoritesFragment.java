package edu.sjsu.android.localeventfinder;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements OnEventCardClickedListener {

    private ArrayList<Event> favoriteEventList;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteEventList = new ArrayList<>();
        favoriteEventList.add(new Event(R.drawable.account_icon, R.string.event_name_1, R.string.event_date_1, R.string.event_location_1, R.string.event_desc_1));
        favoriteEventList.add(new Event(R.drawable.account_icon, R.string.event_name_2, R.string.event_date_2, R.string.event_location_2, R.string.event_desc_2));
        favoriteEventList.add(new Event(R.drawable.account_icon, R.string.event_name_3, R.string.event_date_3, R.string.event_location_3, R.string.event_desc_3));
        favoriteEventList.add(new Event(R.drawable.account_icon, R.string.event_name_4, R.string.event_date_4, R.string.event_location_4, R.string.event_desc_4));
        favoriteEventList.add(new Event(R.drawable.account_icon, R.string.event_name_5, R.string.event_date_5, R.string.event_location_5, R.string.event_desc_5));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Find the RecyclerView in the inflated layout
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter with the event list and set click listener
        adapter = new MyAdapter(favoriteEventList);
        // Assuming MyAdapter has a method to set the click listener
        // adapter.setOnEventCardClickedListener(this);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(int position) {
        if (position != favoriteEventList.size() - 1) {
            goDetail(position);
        }
    }

    private void goDetail(int position) {
        System.out.print("Detail");
        // Implement your detail view navigation logic here
    }
}