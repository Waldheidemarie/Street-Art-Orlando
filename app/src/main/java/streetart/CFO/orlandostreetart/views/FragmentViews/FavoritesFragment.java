package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;

/**
 * Created by Eric on 3/13/2019.
 */
public class FavoritesFragment extends Fragment {

    @BindView(R.id.tvLoginRequired)
    TextView tvLoginRequired;

    public FavoritesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);

        tvLoginRequired.setVisibility(View.VISIBLE);

        return view;
    }
}
