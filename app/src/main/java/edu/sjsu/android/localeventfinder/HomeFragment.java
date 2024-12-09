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

import androidx.lifecycle.ViewModelProvider;

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
        nearestEventsAdapter = new MyAdapter(new ArrayList<>(), true);
        nearestEventsRecyclerView.setAdapter(nearestEventsAdapter);

        moreEventsRecyclerView = view.findViewById(R.id.more_events_recycler);
        moreEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moreEventsAdapter = new MyAdapter(new ArrayList<>(), false);
        moreEventsRecyclerView.setAdapter(moreEventsAdapter);

        List<Event> hardcodedEvents = getHardcodedEvents();

        nearestEventsAdapter.updateData(hardcodedEvents);
        moreEventsAdapter.updateData(hardcodedEvents);

        // Set click listener for event items
        nearestEventsAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            public void onItemClick(Event event) {
                navigateToEventDetail(event);
            }
        });

        moreEventsAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            public void onItemClick(Event event) {
                navigateToEventDetail(event);
            }
        });

        return view;
    }

    private List<Event> getHardcodedEvents() {
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
        events.add(new Event("https://sjmusart.org/sites/default/files/styles/wide/public/2023-05/sjma_11-16-22_a-point-stretched_015_52798444769_o.jpg?itok=5-l-M5l3", "San Jose Museum of Art Exhibition", "San Jose, CA", "2024-12-26", "Enjoy a world-class exhibition showcasing contemporary art at the San Jose Museum of Art.", 37.3349, -121.8873));
        events.add(new Event("https://www.fairmont.com/assets/0/104/2181/2186/4806/7424/8bf95135-3716-4883-9d75-5dcd3122163f.jpg", "New Year's Eve at The Fairmont", "San Jose, CA", "2024-12-31", "Celebrate New Year's Eve in style with a luxurious party at The Fairmont Hotel.", 37.3376, -121.8940));
        events.add(new Event("https://images.squarespace-cdn.com/content/v1/5fd3c36b42b3ce0af68e50ba/f8a11e31-2488-4ce7-8741-f6edb411b807/i-6pLfJkv.jpg", "San Pedro Square Market Food Festival", "San Jose, CA", "2024-12-14", "Taste delicious local food from some of the best food trucks and vendors at San Pedro Square.", 37.3350, -121.8947));
        events.add(new Event("https://img.grouponcdn.com/deal/j2MvyARVmg9eWChPm7aR/XV-700x420/v1/t2100x1260.webp", "Holiday Train at the Children’s Discovery Museum", "San Jose, CA", "2024-12-15", "Take a ride on the Holiday Train at the Children’s Discovery Museum and enjoy festive activities.", 37.3345, -121.8887));
        events.add(new Event("https://cloudfront-us-east-1.images.arcpublishing.com/advancelocal/DGD25FS4AZA7VMEQLACWS5VKBQ.jpg", "Tech Museum of Innovation - Winter Showcase", "San Jose, CA", "2024-12-17", "Explore the Winter Showcase at the Tech Museum and enjoy hands-on exhibits.", 37.3340, -121.8888));
        events.add(new Event("https://christmasmarketusa.com/wp-content/uploads/2024/06/image-139-1024x590.jpeg", "San Jose Holiday Market", "San Jose, CA", "2024-12-18", "Find unique gifts, festive foods, and crafts at the San Jose Holiday Market.", 37.3382, -121.8863));
        events.add(new Event("https://img.grouponcdn.com/deal/moHG2jc1dMWgoz7nkccorNeWTLc/mo-2048x1229/v1/t2100x1260.webp", "Holiday Lights at Gilroy Gardens", "Gilroy, CA", "2024-12-20", "Explore a winter wonderland of festive lights and decorations at Gilroy Gardens.", 37.0030, -121.5787));
        events.add(new Event("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4HqijT4MSrkRW8_BXEz_K7ZN-_Ix8fcQVCw&s", "Tech Santa - Holiday Toy Drive", "San Jose, CA", "2024-12-22", "Join us in giving back to the community by donating toys for children in need.", 37.3350, -121.8878));
        events.add(new Event("https://cdn.shortpixel.ai/spai/q_lossy+ret_img+to_webp/sf.funcheap.com/wp-content/uploads/2016/12/night_shot_of_citp-563x367.jpg", "Christmas Eve at San Jose Convention Center", "San Jose, CA", "2024-12-24", "Experience an unforgettable Christmas Eve celebration with live music and performances.", 37.3360, -121.8875));
        events.add(new Event("https://ceoldigital.com/wp-content/uploads/2024/08/Upcoming-Festivals-events-San-Jose-Fall-Winter-2024-2025.jpg", "Annual San Jose Winter Parade", "San Jose, CA", "2024-12-16", "The city’s annual winter parade with floats, marching bands, and holiday cheer.", 37.3382, -121.8863));
        events.add(new Event("https://images.squarespace-cdn.com/content/v1/5c7be58cb7c92c5aeea73009/1639461746366-B0VOQPY8RLC3JW6XKHLT/THC-NYD-global-hike_arthur-poulin.jpg?format=1500w", "New Year's Day Hike", "San Jose, CA", "2025-01-01", "Start the year right with a refreshing hike through the beautiful Santa Teresa Park.", 37.2513, -121.8413));

        return events;
    }

    private void navigateToEventDetail(Event event) {
        Intent intent = new Intent(getActivity(), EventDetailFragmentActivity.class);
        intent.putExtra("event", event); // Pass the event object
        startActivity(intent);
    }
}






