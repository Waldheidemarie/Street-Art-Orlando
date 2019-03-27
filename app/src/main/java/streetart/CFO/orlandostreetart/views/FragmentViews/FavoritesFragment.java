package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.adapters.MainRecyclerviewAdapter;
import streetart.CFO.orlandostreetart.models.Favorites;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.presenters.FragmentPresenters.FavoritesPresenter;
import streetart.CFO.orlandostreetart.views.Details;

/**
 * Created by Eric on 3/13/2019.
 */
public class FavoritesFragment extends Fragment implements MainRecyclerviewAdapter.OnArtClicked {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tvLoginRequired)
    TextView tvLoginRequired;
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;

    private static final String TAG = "FavoritesFragment";
    FavoritesPresenter favoritesPresenter = new FavoritesPresenter(this);
    Favorites artData;

    public FavoritesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);

        favoritesPresenter.getData();

        tvLoginRequired.setVisibility(View.VISIBLE);

        return view;
    }

    public void setupAdapter(Favorites data) {
        tvLoginRequired.setVisibility(View.INVISIBLE);
        pbProgress.setVisibility(View.GONE);
        this.artData = data;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new MainRecyclerviewAdapter(getActivity(), this, data));
    }


    @Override
    public void OnArtClickedDetails(int position) {
//        Intent showArtDetails = new Intent(getActivity(), Details.class);
//        showArtDetails.putExtra("artDetails", artData.getSubmissions().get(position));
//        startActivity(showArtDetails);
    }
}
