package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.adapters.MainRecyclerviewAdapter;
import streetart.CFO.orlandostreetart.models.GetSubmissions;

/**
 * Created by Eric on 3/13/2019.
 */
public class ExploreFragment extends Fragment {



    public ExploreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // 2. set layoutManger
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // this is data fro recycler view
        ItemData itemsData[] = {
                new ItemData(R.drawable.dummy_graffiti_1),
                new ItemData(R.drawable.dummy_graffiti_2),
                new ItemData(R.drawable.dummy_graffiti_3),
                new ItemData(R.drawable.dummy_graffiti_4),
                new ItemData(R.drawable.dummy_graffiti_5),
                new ItemData(R.drawable.dummy_graffiti_6),
                new ItemData(R.drawable.dummy_graffiti_7),
                new ItemData(R.drawable.dummy_graffiti_8),
                new ItemData(R.drawable.dummy_graffiti_9),
                new ItemData(R.drawable.dummy_graffiti_10),
                new ItemData(R.drawable.dummy_graffiti_11),
        };


        // 3. create an adapter
        MainRecyclerviewAdapter mAdapter = new MainRecyclerviewAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    public class ItemData {
        int picture;

        public ItemData(int picture) {
            this.picture = picture;
        }

        public int getPicture() {
            return picture;
        }

        public void setPicture(int picture) {
            this.picture = picture;
        }
    }
}
