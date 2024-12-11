package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView favoritesRecyclerView;
    private MyAdapter favoritesAdapter;
    private List<Event> favoritesList;
    private EventDatabaseHelper eventDatabaseHelper;
    private TextView noFavoritesText;

    public FavoritesFragment() {
        // Default constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database helper
        eventDatabaseHelper = new EventDatabaseHelper(getContext());

        // Initialize the favorites list
        favoritesList = new ArrayList<>();

        // Load favorited events from database
        loadFavoriteEvents();
    }

    private void loadFavoriteEvents() {
        favoritesList.clear();
        List<Event> favEvents = eventDatabaseHelper.getFavoriteEvents();
        favoritesList.addAll(favEvents);

        // Update adapter if it exists
        if (favoritesAdapter != null) {
            favoritesAdapter.notifyDataSetChanged();
        }

        // Update empty state message if view is created
        updateEmptyState();
    }

    private void updateEmptyState() {
        if (noFavoritesText != null) {
            if (favoritesList.isEmpty()) {
                noFavoritesText.setVisibility(View.VISIBLE);
                favoritesRecyclerView.setVisibility(View.GONE);
            } else {
                noFavoritesText.setVisibility(View.GONE);
                favoritesRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Initialize views
        favoritesRecyclerView = view.findViewById(R.id.favorites_recycler);
        noFavoritesText = view.findViewById(R.id.no_favorites_text);

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with favoritesList
        favoritesAdapter = new MyAdapter(favoritesList, false);

        // Add click listener to navigate to EventDetailFragmentActivity
        favoritesAdapter.setOnEventCardClickedListener(position -> {
            Event event = favoritesList.get(position);
            Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        favoritesRecyclerView.setAdapter(favoritesAdapter);

        // Show empty state message if needed
        updateEmptyState();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload favorites when returning to this fragment
        loadFavoriteEvents();
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