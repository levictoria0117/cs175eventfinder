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
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView nearestEventsRecyclerView, moreEventsRecyclerView;
    private MyAdapter nearestEventsAdapter, moreEventsAdapter;
    private EventViewModel eventViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        nearestEventsRecyclerView = view.findViewById(R.id.nearest_events_recycler);
        nearestEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        nearestEventsAdapter = new MyAdapter(new ArrayList<>(), true, getContext());
        nearestEventsRecyclerView.setAdapter(nearestEventsAdapter);

        moreEventsRecyclerView = view.findViewById(R.id.more_events_recycler);
        moreEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moreEventsAdapter = new MyAdapter(new ArrayList<>(), false, getContext());
        moreEventsRecyclerView.setAdapter(moreEventsAdapter);

        List<Event> hardcodedEvents = getHardcodedEvents();

        nearestEventsAdapter.updateData(hardcodedEvents);
        moreEventsAdapter.updateData(hardcodedEvents);

        nearestEventsAdapter.setOnItemClickListener(event -> navigateToEventDetail(event));
        moreEventsAdapter.setOnItemClickListener(event -> navigateToEventDetail(event));

        nearestEventsAdapter.setOnFavoriteClickListener((event, isFavorite) -> eventViewModel.updateEvent(event));
        moreEventsAdapter.setOnFavoriteClickListener((event, isFavorite) -> eventViewModel.updateEvent(event));

        return view;
    }

    private List<Event> getHardcodedEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("https://sanjoseholidayparade.com/wp-content/uploads/2019/08/SJ-Holiday-Parade-2006-358.jpg", "San Jose Holiday Parade", "San Jose, CA", "2024-12-15", "Join us for the annual San Jose Holiday Parade with floats, music, and festive fun!", 37.3382, -121.8863));
        events.add(new Event("https://images.squarespace-cdn.com/content/v1/651075c1ac6d252b427acf9a/93eddb7f-28c2-4554-a579-2c7b4f1a295c/13-min.jpg", "Downtown Ice Skating", "San Jose, CA", "2024-12-16", "Outdoor ice skating rink in the heart of downtown San Jose. Perfect for the whole family.", 37.3382, -121.8853));
        events.add(new Event("https://www.metrosiliconvalley.com/wp-content/uploads/sites/16/2019/12/san-jose-christmas-in-the-park-world-record-trees-1-1024x703-1-1024x580.jpg", "Christmas in the Park", "San Jose, CA", "2024-12-17", "Experience the magic of Christmas with thousands of lights, holiday displays, and seasonal activities.", 37.3376, -121.8882));
        events.add(new Event("https://cdn.kqed.org/wp-content/uploads/sites/2/2018/02/La_Misa_Negra_PREFERRED_sop01_rgb_md-800x401.jpg", "San Jose Jazz Winter Fest", "San Jose, CA", "2024-12-18", "A cozy celebration of live jazz music, local artists, and holiday cheer.", 37.3352, -121.8815));
        events.add(new Event("https://media.licdn.com/dms/image/v2/C561BAQGT5vzAKJm6qg/company-background_10000/company-background_10000/0/1585488328418/santa_clara_county_fairgrounds_cover?e=2147483647&v=beta&t=MBSzJgUZbbFE3_kxlHzMd8z007JRTNCh1yZRJEIZess", "Santa Clara County Fairgrounds Holiday Market", "San Jose, CA", "2024-12-19", "Shop for unique gifts, artisanal foods, and crafts at the Holiday Market.", 37.3163, -121.9467));
        return events;
    }

    private void navigateToEventDetail(Event event) {
        Intent intent = new Intent(getActivity(), EventDetailFragmentActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }
}







