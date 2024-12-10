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

public class FavoritesFragment extends Fragment {

    private RecyclerView favoritesRecyclerView;
    private MyAdapter favoritesAdapter;
    private List<Event> favoritesList;

    public FavoritesFragment() {
        // Default constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the favorites list from MainActivity or a shared adapter
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            favoritesList = ((MainActivity) getActivity()).getFavoritesList();
        }

        // Fallback if the above fails
        if (favoritesList == null) {
            favoritesList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoritesRecyclerView = view.findViewById(R.id.favorites_recycler);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with favoritesList
        favoritesAdapter = new MyAdapter(favoritesList, true);

        // Add click listener to navigate to EventDetailFragmentActivity
        favoritesAdapter.setOnEventCardClickedListener(position -> {
            Event event = favoritesList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        favoritesRecyclerView.setAdapter(favoritesAdapter);

        return view;
    }
}


