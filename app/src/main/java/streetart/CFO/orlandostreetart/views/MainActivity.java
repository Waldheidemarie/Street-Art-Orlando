package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private static final String TAG = "MainActivity";
    final FragmentManager fm = getSupportFragmentManager();
    MainPresenter presenter = new MainPresenter(this, fm);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter.getFragments();

        navigation.setOnNavigationItemSelectedListener(presenter.mOnNavigationItemSelectedListener);
    }

}
