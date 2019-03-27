package streetart.CFO.orlandostreetart.adapters;

import android.content.Context;
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
import streetart.CFO.orlandostreetart.models.GetModel;

/**
 * Created by Eric on 3/14/2019.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private GetModel itemsData;
    private Context context;
    private OnArtClicked onArtClicked;


    public RecyclerViewAdapter(Context context, OnArtClicked onArtClicked, GetModel itemsData) {
        this.itemsData = itemsData;
        this.context = context;
        this.onArtClicked = onArtClicked;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, final int position) {
//         Load Image
        Glide.with(context)
                .load(itemsData.getSubmissions().get(position).getPhotoUrl())
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
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder(view, onArtClicked) {
        };
    }

    public interface OnArtClicked {
        void OnArtClickedDetails(int position);
    }
}
