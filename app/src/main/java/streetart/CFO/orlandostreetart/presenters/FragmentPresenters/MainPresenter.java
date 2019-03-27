package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.views.FragmentViews.ExploreFragment;
import streetart.CFO.orlandostreetart.views.FragmentViews.FavoritesFragment;
import streetart.CFO.orlandostreetart.views.FragmentViews.SettingsFragment;

/**
 * Created by Eric on 3/13/2019.
 */
public class MainPresenter {

    private static final String TAG = "MainPresenter";
    private final Fragment explore = new ExploreFragment();
    private final Fragment favorites = new FavoritesFragment();
    private final Fragment settings = new SettingsFragment();
    private final FragmentManager fm;

    public MainPresenter(FragmentManager fm) {
        this.fm = fm;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    fm.beginTransaction().replace(R.id.main_container, explore).commit();
                        return true;
                case R.id.navigation_favorites:
                    fm.beginTransaction().replace(R.id.main_container, favorites).commit();
                        return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().replace(R.id.main_container, settings).commit();
                        return true;
            }
            return false;
        }
    };

    public void getFragments(){
        fm.beginTransaction().add(R.id.main_container, explore).commit();
    }
}
