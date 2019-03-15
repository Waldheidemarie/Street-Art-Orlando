package streetart.CFO.orlandostreetart.adapters;

import android.content.Intent;
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
import streetart.CFO.orlandostreetart.views.Details;
import streetart.CFO.orlandostreetart.views.ExploreFragment;

/**
 * Created by Eric on 3/14/2019.
 */
public class MainRecyclerviewAdapter extends RecyclerView.Adapter<MainRecyclerviewAdapter.ViewHolder> {

    private GetSubmissions itemsData;
    private ExploreFragment context;


    public MainRecyclerviewAdapter(ExploreFragment exploreFragment, GetSubmissions itemsData) {
        this.itemsData = itemsData;
        this.context = exploreFragment;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerviewAdapter.ViewHolder viewHolder, int position) {
//         Load Image
        Glide.with(context)
                .load(itemsData.getSubmissions().get(position).getThumbUrl())
                .into(viewHolder.mImageView);

        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDetails = new Intent(v.getContext() , Details.class);
                context.startActivity(openDetails);
            }
        });
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
        return itemsData.getSubmissions().size();
    }

    @NonNull
    @Override
    public MainRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder(view) {
        };
    }
}
