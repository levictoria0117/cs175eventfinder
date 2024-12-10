package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter eventAdapter;
    private ArrayList<Event> allEventsList;
    private TextView noResultsText;
    private Spinner sortSpinner;
    private String currentQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize views
        noResultsText = findViewById(R.id.no_results_text);
        sortSpinner = findViewById(R.id.sort_spinner);

        // Setup spinner
        setupSortSpinner();

        // Initialize events list and RecyclerView
        initializeEventsList();
        setupRecyclerView();

        // Setup search functionality
        setupSearchView();
    }

    private void setupSortSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterAndSortEvents(currentQuery);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.search_results_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.VISIBLE);

        eventAdapter = new MyAdapter(allEventsList, false);
        recyclerView.setAdapter(eventAdapter);

        eventAdapter.setOnEventCardClickedListener(position -> {
            Event event = allEventsList.get(position);
            Intent intent = new Intent(this, EventDetailFragmentActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });
    }

    private void setupSearchView() {
        SearchView searchBar = findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentQuery = query;
                filterAndSortEvents(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentQuery = newText;
                filterAndSortEvents(newText);
                return true;
            }
        });
    }

    private void initializeEventsList() {
        allEventsList = new ArrayList<>();
        allEventsList.add(new Event("https://sanjoseholidayparade.com/wp-content/uploads/2019/08/SJ-Holiday-Parade-2006-358.jpg", "San Jose Holiday Parade", "San Jose, CA", "2024-12-15", "Join us for the annual San Jose Holiday Parade with floats, music, and festive fun!", 37.3382, -121.8863));
        allEventsList.add(new Event("https://images.squarespace-cdn.com/content/v1/651075c1ac6d252b427acf9a/93eddb7f-28c2-4554-a579-2c7b4f1a295c/13-min.jpg", "Downtown Ice Skating", "San Jose, CA", "2024-12-16", "Outdoor ice skating rink in the heart of downtown San Jose. Perfect for the whole family.", 37.3382, -121.8853));
        allEventsList.add(new Event("https://www.metrosiliconvalley.com/wp-content/uploads/sites/16/2019/12/san-jose-christmas-in-the-park-world-record-trees-1-1024x703-1-1024x580.jpg", "Christmas in the Park", "San Jose, CA", "2024-12-17", "Experience the magic of Christmas with thousands of lights, holiday displays, and seasonal activities.", 37.3376, -121.8882));
        allEventsList.add(new Event("https://cdn.kqed.org/wp-content/uploads/sites/2/2018/02/La_Misa_Negra_PREFERRED_sop01_rgb_md-800x401.jpg", "San Jose Jazz Winter Fest", "San Jose, CA", "2024-12-18", "A cozy celebration of live jazz music, local artists, and holiday cheer.", 37.3352, -121.8815));
        allEventsList.add(new Event("https://media.licdn.com/dms/image/v2/C561BAQGT5vzAKJm6qg/company-background_10000/company-background_10000/0/1585488328418/santa_clara_county_fairgrounds_cover?e=2147483647&v=beta&t=MBSzJgUZbbFE3_kxlHzMd8z007JRTNCh1yZRJEIZess", "Santa Clara County Fairgrounds Holiday Market", "San Jose, CA", "2024-12-19", "Shop for unique gifts, artisanal foods, and crafts at the Holiday Market.", 37.3163, -121.9467));
        allEventsList.add(new Event("https://www.bothman.com/wp-content/uploads/2023/10/Avaya-Stadium-1.jpg", "San Jose Earthquakes Soccer Game", "San Jose, CA", "2024-12-20", "Cheer on the San Jose Earthquakes in their last match of the season at PayPal Park.", 37.3328, -121.9008));
        allEventsList.add(new Event("https://www.thepierce.com/wp-content/uploads/2016/12/pierce_lights.jpg", "Winter Wonderland at Vasona Lake", "Los Gatos, CA", "2024-12-21", "Celebrate the season at Vasona Lake with a winter light display, train rides, and family-friendly activities.", 37.2391, -121.9718));
        allEventsList.add(new Event("https://streamline.imgix.net/a772b068-76bf-4e49-880d-090dc5c1d0e8/722b388f-0e54-4041-8b70-176147dc192c/IMG_5061.JPG?ixlib=rb-1.1.0&w=2000&h=2000&fit=max&or=0&s=6355c16e24c1d58347c29f32348280f9", "Holiday Craft Fair", "San Jose, CA", "2024-12-22", "Explore handmade gifts, crafts, and delicious food at the San Jose Holiday Craft Fair.", 37.3342, -121.8893));
        allEventsList.add(new Event("https://www.sjpl.org/wp-content/uploads/sites/142/2024/04/chiavari-1024x640.png", "Cirque du Soleil - OVO", "San Jose, CA", "2024-12-23", "A thrilling acrobatic performance filled with humor and amazing circus acts.", 37.3395, -121.8877));
        allEventsList.add(new Event("https://i.ytimg.com/vi/YZ1o0dm-HsY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA0D-OEYF-pWKvizVwqZ9LHDhDC6Q", "Christmas Eve Service at Willow Glen Baptist Church", "San Jose, CA", "2024-12-24", "A peaceful Christmas Eve service with candlelight, music, and communion.", 37.2954, -121.8981));
        allEventsList.add(new Event("https://sjmusart.org/sites/default/files/styles/wide/public/2023-05/sjma_11-16-22_a-point-stretched_015_52798444769_o.jpg?itok=5-l-M5l3", "San Jose Museum of Art Exhibition", "San Jose, CA", "2024-12-26", "Enjoy a world-class exhibition showcasing contemporary art at the San Jose Museum of Art.", 37.3349, -121.8873));
        allEventsList.add(new Event("https://www.fairmont.com/assets/0/104/2181/2186/4806/7424/8bf95135-3716-4883-9d75-5dcd3122163f.jpg", "New Year's Eve at The Fairmont", "San Jose, CA", "2024-12-31", "Celebrate New Year's Eve in style with a luxurious party at The Fairmont Hotel.", 37.3376, -121.8940));
        allEventsList.add(new Event("https://images.squarespace-cdn.com/content/v1/5fd3c36b42b3ce0af68e50ba/f8a11e31-2488-4ce7-8741-f6edb411b807/i-6pLfJkv.jpg", "San Pedro Square Market Food Festival", "San Jose, CA", "2024-12-14", "Taste delicious local food from some of the best food trucks and vendors at San Pedro Square.", 37.3350, -121.8947));
        allEventsList.add(new Event("https://img.grouponcdn.com/deal/j2MvyARVmg9eWChPm7aR/XV-700x420/v1/t2100x1260.webp", "Holiday Train at the Children’s Discovery Museum", "San Jose, CA", "2024-12-15", "Take a ride on the Holiday Train at the Children’s Discovery Museum and enjoy festive activities.", 37.3345, -121.8887));
        allEventsList.add(new Event("https://cloudfront-us-east-1.images.arcpublishing.com/advancelocal/DGD25FS4AZA7VMEQLACWS5VKBQ.jpg", "Tech Museum of Innovation - Winter Showcase", "San Jose, CA", "2024-12-17", "Explore the Winter Showcase at the Tech Museum and enjoy hands-on exhibits.", 37.3340, -121.8888));
        allEventsList.add(new Event("https://christmasmarketusa.com/wp-content/uploads/2024/06/image-139-1024x590.jpeg", "San Jose Holiday Market", "San Jose, CA", "2024-12-18", "Find unique gifts, festive foods, and crafts at the San Jose Holiday Market.", 37.3382, -121.8863));
        allEventsList.add(new Event("https://img.grouponcdn.com/deal/moHG2jc1dMWgoz7nkccorNeWTLc/mo-2048x1229/v1/t2100x1260.webp", "Holiday Lights at Gilroy Gardens", "Gilroy, CA", "2024-12-20", "Explore a winter wonderland of festive lights and decorations at Gilroy Gardens.", 37.0030, -121.5787));
        allEventsList.add(new Event("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4HqijT4MSrkRW8_BXEz_K7ZN-_Ix8fcQVCw&s", "Tech Santa - Holiday Toy Drive", "San Jose, CA", "2024-12-22", "Join us in giving back to the community by donating toys for children in need.", 37.3350, -121.8878));
        allEventsList.add(new Event("https://cdn.shortpixel.ai/spai/q_lossy+ret_img+to_webp/https://www.siliconvalleyconference.com/wp-content/uploads/2020/02/Downtown-San-Jose-2.jpg", "Winter Festival at San Jose State University", "San Jose, CA", "2024-12-24", "Enjoy ice skating, hot cocoa, and festive activities at San Jose State's Winter Festival.", 37.3365, -121.8810));
    }

    private void filterAndSortEvents(String text) {
        ArrayList<Event> filteredList = new ArrayList<>();

        // Filter events
        for (Event event : allEventsList) {
            // Access event properties directly
            String eventName = event.getEventName().toLowerCase();
            String eventLocation = event.getLocation().toLowerCase();
            String eventDate = event.getDate().toLowerCase();

            // Check if the event details contain the search text
            if (eventName.contains(text.toLowerCase()) ||
                    eventLocation.contains(text.toLowerCase()) ||
                    eventDate.contains(text.toLowerCase())) {
                filteredList.add(event);
            }
        }

        // Sort events based on selected option
        int sortOption = sortSpinner.getSelectedItemPosition();
        sortEvents(filteredList, sortOption);

        // Update UI with filtered and sorted events
        updateUI(filteredList);
    }

    private void sortEvents(ArrayList<Event> events, int sortOption) {
        switch (sortOption) {
            case 0: // Date (newest first)
                Collections.sort(events, (e1, e2) -> e2.getDate().compareTo(e1.getDate()));
                break;
            case 1: // Date (oldest first)
                Collections.sort(events, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
                break;
            case 2: // Name (A-Z)
                Collections.sort(events, (e1, e2) -> e1.getEventName().compareTo(e2.getEventName()));
                break;
            case 3: // Name (Z-A)
                Collections.sort(events, (e1, e2) -> e2.getEventName().compareTo(e1.getEventName()));
                break;
        }
    }

    private void updateUI(ArrayList<Event> filteredList) {
        if (filteredList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noResultsText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noResultsText.setVisibility(View.GONE);

            eventAdapter = new MyAdapter(filteredList, false);
            recyclerView.setAdapter(eventAdapter);

            eventAdapter.setOnEventCardClickedListener(position -> {
                Event event = filteredList.get(position);
                Intent intent = new Intent(this, EventDetailFragmentActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            });
        }
    }


}





