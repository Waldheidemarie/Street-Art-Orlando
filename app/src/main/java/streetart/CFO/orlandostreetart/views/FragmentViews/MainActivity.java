package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.FragmentPresenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private static final String TAG = "MainActivity";
    final FragmentManager fm = getSupportFragmentManager();
    MainPresenter presenter = new MainPresenter(fm);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

            presenter.getFragments();

        navigation.setOnNavigationItemSelectedListener(presenter.mOnNavigationItemSelectedListener);
    }
}
