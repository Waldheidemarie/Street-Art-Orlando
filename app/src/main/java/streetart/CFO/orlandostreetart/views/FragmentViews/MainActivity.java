package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private static final String TAG = "MainActivity";
    final FragmentManager fm = getSupportFragmentManager();

    private final Fragment explore = new ExploreFragment();
    private final Fragment favorites = new FavoritesFragment();
    private final Fragment settings = new SettingsFragment();
    private Fragment active = explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            active = fm.getFragment(savedInstanceState, "fragment");
            fm.beginTransaction().replace(R.id.main_container, active).commit();
        } else {
            fm.beginTransaction().add(R.id.main_container, explore).commit();
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    fm.beginTransaction().replace(R.id.main_container, explore).commit();
                    active = explore;
                    return true;
                case R.id.navigation_favorites:
                    fm.beginTransaction().replace(R.id.main_container, favorites).commit();
                    active = favorites;
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().replace(R.id.main_container, settings).commit();
                    active = settings;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fm.putFragment(outState,"fragment",active);
    }

}
