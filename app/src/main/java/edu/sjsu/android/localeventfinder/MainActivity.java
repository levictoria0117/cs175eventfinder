package edu.sjsu.android.localeventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String userEmail;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapter = new MyAdapter(new ArrayList<>(), true);

        userEmail = getIntent().getStringExtra("USER_EMAIL");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HomeFragment(), "Home");
        vpAdapter.addFragment(new FavoritesFragment(), "Favorite");
        vpAdapter.addFragment(new TicketsFragment(), "Tickets");

        SettingsFragment settingsFragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("USER_EMAIL", userEmail);
        settingsFragment.setArguments(bundle);
        vpAdapter.addFragment(settingsFragment, "Settings");

        viewPager.setAdapter(vpAdapter);

        setupTabIcons();
    }

    public MyAdapter getAdapter() {
        return myAdapter;
    }

    public List<Event> getFavoritesList() {
        return myAdapter.getFavoritesList();
    }

    private void setupTabIcons() {
        int iconSizeInDp = 70;

        int iconSizeInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                iconSizeInDp,
                getResources().getDisplayMetrics()
        );

        int[] tabIcons = {
                R.drawable.home_icon,
                R.drawable.favorites_icon,
                R.drawable.tickets_icon,
                R.drawable.account_icon
        };

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setText(null);
                tab.setIcon(tabIcons[i]);

                LinearLayout layout = (LinearLayout) tab.view;
                ImageView imageView = (ImageView) layout.getChildAt(0);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        iconSizeInPx,
                        iconSizeInPx
                );
                params.setMargins(0, 10, 0, 10);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        }
    }
}
