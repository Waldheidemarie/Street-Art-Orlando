package streetart.CFO.orlandostreetart.adapters;

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
import streetart.CFO.orlandostreetart.views.MyPhotos;

/**
 * Created by Eric on 3/14/2019.
 */
public class MyPhotosRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotosRecyclerViewAdapter.ViewHolder> {

    private GetModel myPhotos;
    private MyPhotos myPhotosView;


    public MyPhotosRecyclerViewAdapter(MyPhotos myPhotosView, GetModel myPhotos) {
        this.myPhotosView = myPhotosView;
        this.myPhotos = myPhotos;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotosRecyclerViewAdapter.ViewHolder viewHolder, final int position) {
//        Set Title
        String title = myPhotos.getSubmissions().get(position).getTitle();
        if (!title.equals(""))
            viewHolder.tvTitle.setText(title);

//        Set Artist
        String artist = myPhotos.getSubmissions().get(position).getArtist();
        if (!artist.equals(""))
            viewHolder.tvArtist.setText(artist);

//        Set Status
        String status = myPhotos.getSubmissions().get(position).getStatus().toLowerCase();
        String upperString = status.substring(0,1).toUpperCase() + status.substring(1);
        viewHolder.tvStatus.setText(upperString);
        if (status.equals("rejected")) {
            viewHolder.tvStatus.setTextColor(myPhotosView.getResources().getColor(R.color.red));
        }
        if (status.equals("approved")){
            viewHolder.tvStatus.setTextColor(myPhotosView.getResources().getColor(R.color.Green));
        }


//         Load Image
        Glide.with(myPhotosView)
                .load(myPhotos.getSubmissions().get(position).getPhotoUrl())
                .into(viewHolder.imgArtwork);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
//        Bind IDs

        @BindView(R.id.imgArtwork)
        ImageView imgArtwork;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvArtist)
        TextView tvArtist;
        @BindView(R.id.tvStatus)
        TextView tvStatus;

        ViewHolder(@NonNull View itemView) {
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
