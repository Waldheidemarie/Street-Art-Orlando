package streetart.CFO.orlandostreetart.presenters;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.views.ExploreFragment;
import streetart.CFO.orlandostreetart.views.FavoritesFragment;
import streetart.CFO.orlandostreetart.views.MainActivity;
import streetart.CFO.orlandostreetart.views.SettingsFragment;

/**
 * Created by Eric on 3/13/2019.
 */
public class MainPresenter {

    private static final String TAG = "MainPresenter";
    private MainActivity mainActivity;
    private final Fragment explore = new ExploreFragment();
    private final Fragment favorites = new FavoritesFragment();
    private final Fragment settings = new SettingsFragment();
    private final FragmentManager fm;
    private Fragment active = explore;

    public MainPresenter(MainActivity mainActivity, FragmentManager fm) {
        this.mainActivity = mainActivity;
        this.fm = fm;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    fm.beginTransaction().hide(active).show(explore).commit();
                    active = explore;
                    return true;
                case R.id.navigation_favorites:
                    fm.beginTransaction().hide(active).show(favorites).commit();
                    active = favorites;
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().hide(active).show(settings).commit();
                    active = settings;
                    return true;
            }
            return false;
        }
    };

    public void getFragments(){
        fm.beginTransaction().add(R.id.main_container, settings, "3").hide(settings).commit();
        fm.beginTransaction().add(R.id.main_container, favorites, "2").hide(favorites).commit();
        fm.beginTransaction().add(R.id.main_container, explore, "1").commit();
    }
}
