package streetart.CFO.orlandostreetart.views;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.adapters.MainRecyclerviewAdapter;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.presenters.RecyclerViewPresenter;

/**
 * Created by Eric on 3/13/2019.
 */
public class ExploreFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final String TAG = "ExploreFragment";
    RecyclerViewPresenter rvPresenter = new RecyclerViewPresenter(this);

    public ExploreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);

        rvPresenter.getData();

        return view;
    }

    public void setupAdapter(GetSubmissions data){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new MainRecyclerviewAdapter(this, data));
    }
}
