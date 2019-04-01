package streetart.CFO.orlandostreetart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.GetModel;

/**
 * Created by Eric on 3/14/2019.
 */
public class MyPhotosRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotosRecyclerViewAdapter.ViewHolder> {

    private GetModel myPhotos;


    public MyPhotosRecyclerViewAdapter(GetModel myPhotos) {
        this.myPhotos = myPhotos;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotosRecyclerViewAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tvTitle.setText(myPhotos.getSubmissions().get(position).getTitle());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        Bind IDs

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return myPhotos.getSubmissions().size();
    }

    @NonNull
    @Override
    public MyPhotosRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_photos_layout, viewGroup, false);
        return new ViewHolder(view) {
        };
    }
}
