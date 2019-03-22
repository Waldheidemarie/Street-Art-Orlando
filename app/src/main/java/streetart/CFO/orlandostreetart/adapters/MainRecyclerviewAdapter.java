package streetart.CFO.orlandostreetart.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.FragmentViews.ExploreFragment;

/**
 * Created by Eric on 3/14/2019.
 */
public class MainRecyclerviewAdapter extends RecyclerView.Adapter<MainRecyclerviewAdapter.ViewHolder> {

    private static final String TAG = "MainRecyclerviewAdapter";
    private GetSubmissions itemsData;
    private ExploreFragment context;
    private OnArtClicked onArtClicked;


    public MainRecyclerviewAdapter(ExploreFragment exploreFragment, OnArtClicked onArtClicked, GetSubmissions itemsData) {
        this.itemsData = itemsData;
        this.context = exploreFragment;
        this.onArtClicked = onArtClicked;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerviewAdapter.ViewHolder viewHolder, final int position) {
//         Load Image
        Glide.with(context)
                .load(itemsData.getSubmissions().get(position).getThumbUrl())
                .into(viewHolder.mImageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        Bind IDs

        @BindView(R.id.image_view)
        ImageView mImageView;

        OnArtClicked onArtClicked;

        public ViewHolder(@NonNull View itemView, OnArtClicked onArtClicked) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onArtClicked = onArtClicked;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onArtClicked.OnArtClickedDetails(getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.getSubmissions().size();
    }

    @NonNull
    @Override
    public MainRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder(view, onArtClicked) {
        };
    }

    public interface OnArtClicked {
        void OnArtClickedDetails(int position);
    }
}
