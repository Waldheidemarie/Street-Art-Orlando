package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    public void setupAdapter(GetSubmissions datalist){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        MainRecyclerviewAdapter mAdapter = new MainRecyclerviewAdapter(this, datalist);
        recyclerView.setAdapter(mAdapter);
    }

////    todo: delete after using API
//    private ItemData[] dummyData() {
//        ItemData itemsData[] = {
//                new ItemData(R.drawable.dummy_graffiti_1),
//                new ItemData(R.drawable.dummy_graffiti_2),
//                new ItemData(R.drawable.dummy_graffiti_3),
//                new ItemData(R.drawable.dummy_graffiti_4),
//                new ItemData(R.drawable.dummy_graffiti_5),
//                new ItemData(R.drawable.dummy_graffiti_6),
//                new ItemData(R.drawable.dummy_graffiti_7),
//                new ItemData(R.drawable.dummy_graffiti_8),
//                new ItemData(R.drawable.dummy_graffiti_9),
//                new ItemData(R.drawable.dummy_graffiti_10),
//                new ItemData(R.drawable.dummy_graffiti_11),
//        };
//        return itemsData;
//    }
//
//    public class ItemData {
//        int picture;
//
//        public ItemData(int picture) {
//            this.picture = picture;
//        }
//
//        public int getPicture() {
//            return picture;
//        }
//
//        public void setPicture(int picture) {
//            this.picture = picture;
//        }
//    }
}
