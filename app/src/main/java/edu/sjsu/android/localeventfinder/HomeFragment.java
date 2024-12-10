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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the event lists
        nearestEventsList = new ArrayList<>();
        moreEventsList = new ArrayList<>();

        // Add hardcoded events (same as before)
        List<Event> events = new ArrayList<>();
        events.add(new Event("https://sanjoseholidayparade.com/wp-content/uploads/2019/08/SJ-Holiday-Parade-2006-358.jpg", "San Jose Holiday Parade", "San Jose, CA", "2024-12-15", "Join us for the annual San Jose Holiday Parade with floats, music, and festive fun!", 37.3382, -121.8863));
        events.add(new Event("https://images.squarespace-cdn.com/content/v1/651075c1ac6d252b427acf9a/93eddb7f-28c2-4554-a579-2c7b4f1a295c/13-min.jpg", "Downtown Ice Skating", "San Jose, CA", "2024-12-16", "Outdoor ice skating rink in the heart of downtown San Jose. Perfect for the whole family.", 37.3382, -121.8853));
        events.add(new Event("https://www.metrosiliconvalley.com/wp-content/uploads/sites/16/2019/12/san-jose-christmas-in-the-park-world-record-trees-1-1024x703-1-1024x580.jpg", "Christmas in the Park", "San Jose, CA", "2024-12-17", "Experience the magic of Christmas with thousands of lights, holiday displays, and seasonal activities.", 37.3376, -121.8882));
        events.add(new Event("https://cdn.kqed.org/wp-content/uploads/sites/2/2018/02/La_Misa_Negra_PREFERRED_sop01_rgb_md-800x401.jpg", "San Jose Jazz Winter Fest", "San Jose, CA", "2024-12-18", "A cozy celebration of live jazz music, local artists, and holiday cheer.", 37.3352, -121.8815));
        events.add(new Event("https://media.licdn.com/dms/image/v2/C561BAQGT5vzAKJm6qg/company-background_10000/company-background_10000/0/1585488328418/santa_clara_county_fairgrounds_cover?e=2147483647&v=beta&t=MBSzJgUZbbFE3_kxlHzMd8z007JRTNCh1yZRJEIZess", "Santa Clara County Fairgrounds Holiday Market", "San Jose, CA", "2024-12-19", "Shop for unique gifts, artisanal foods, and crafts at the Holiday Market.", 37.3163, -121.9467));
        events.add(new Event("https://www.bothman.com/wp-content/uploads/2023/10/Avaya-Stadium-1.jpg", "San Jose Earthquakes Soccer Game", "San Jose, CA", "2024-12-20", "Cheer on the San Jose Earthquakes in their last match of the season at PayPal Park.", 37.3328, -121.9008));
        events.add(new Event("https://www.thepierce.com/wp-content/uploads/2016/12/pierce_lights.jpg", "Winter Wonderland at Vasona Lake", "Los Gatos, CA", "2024-12-21", "Celebrate the season at Vasona Lake with a winter light display, train rides, and family-friendly activities.", 37.2391, -121.9718));
        events.add(new Event("https://streamline.imgix.net/a772b068-76bf-4e49-880d-090dc5c1d0e8/722b388f-0e54-4041-8b70-176147dc192c/IMG_5061.JPG?ixlib=rb-1.1.0&w=2000&h=2000&fit=max&or=0&s=6355c16e24c1d58347c29f32348280f9", "Holiday Craft Fair", "San Jose, CA", "2024-12-22", "Explore handmade gifts, crafts, and delicious food at the San Jose Holiday Craft Fair.", 37.3342, -121.8893));
        events.add(new Event("https://www.sjpl.org/wp-content/uploads/sites/142/2024/04/chiavari-1024x640.png", "Cirque du Soleil - OVO", "San Jose, CA", "2024-12-23", "A thrilling acrobatic performance filled with humor and amazing circus acts.", 37.3395, -121.8877));
        events.add(new Event("https://i.ytimg.com/vi/YZ1o0dm-HsY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA0D-OEYF-pWKvizVwqZ9LHDhDC6Q", "Christmas Eve Service at Willow Glen Baptist Church", "San Jose, CA", "2024-12-24", "A peaceful Christmas Eve service with candlelight, music, and communion.", 37.2954, -121.8981));

        nearestEventsList.addAll(events.subList(0, 5));
        moreEventsList.addAll(events.subList(5, events.size()));
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

        ImageView searchButton = view.findViewById(R.id.imageView3);
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
}

