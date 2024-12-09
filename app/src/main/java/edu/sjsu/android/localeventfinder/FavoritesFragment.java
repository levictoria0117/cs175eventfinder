package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private EventViewModel eventViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter(new ArrayList<>(), false);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                navigateToEventDetail(event);
            }
        });
        recyclerView.setAdapter(adapter);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getFavoriteEvents().observe(getViewLifecycleOwner(), events -> {
            adapter.updateData(events);
        });

        return view;
    }

    private void navigateToEventDetail(Event event) {
        Intent intent = new Intent(getContext(), EventDetailFragmentActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }
}



