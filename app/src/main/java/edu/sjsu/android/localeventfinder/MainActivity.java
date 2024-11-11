package edu.sjsu.android.localeventfinder;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HomeFragment(), "Home");
        vpAdapter.addFragment(new FavoritesFragment(), "Favorite");
        vpAdapter.addFragment(new TicketsFragment(), "Tickets");
        vpAdapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(vpAdapter);

        setupTabIcons();
    }

    private void setupTabIcons() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.home_icon);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.favorites_icon);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.tickets_icon);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.account_icon);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setText(null);
            }
        }
    }
}