package streetart.CFO.orlandostreetart.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.views.ExploreFragment;

/**
 * Created by Eric on 3/14/2019.
 */
public class MainRecyclerviewAdapter extends RecyclerView.Adapter<MainRecyclerviewAdapter.ViewHolder> {

    ExploreFragment.ItemData[] dummyData;


    public MainRecyclerviewAdapter(ExploreFragment.ItemData[] itemsData) {
        this.dummyData = itemsData;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerviewAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mImageView.setImageResource(dummyData[position].getPicture());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        Bind IDs

        @BindView(R.id.image_view)
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return dummyData.length;
    }

    @NonNull
    @Override
    public MainRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder(view) {
        };
    }
}
