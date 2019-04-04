package streetart.CFO.orlandostreetart.views;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.DetailsPresenter;

/**
 * Created by Eric on 3/15/2019.
 */
public class Details extends AppCompatActivity{

    @BindView(R.id.imgArtwork)
    ImageView imgArtwork;
    @BindView(R.id.tvImageTitle)
    TextView tvImageTitle;
    @BindView(R.id.tvArtistName)
    TextView tvArtistName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvLocationNotes)
    TextView tvLocationNotes;
    @BindView(R.id.tvDescriptionLabel)
    TextView tvDescriptionLabel;
    @BindView(R.id.divider1)
    View viewDivider1;
    @BindView(R.id.divider2)
    View viewDivider2;
    @BindView(R.id.imgFavorite)
    ImageView imgFavorite;
    @BindView(R.id.tvSubmittedBy)
    TextView tvSubmittedBy;

    private static final String TAG = "Details";
    DetailsPresenter detailPresenter = new DetailsPresenter(this);
    GoogleMap mMap;

    public Details() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(detailPresenter);


        detailPresenter.getPassedDetails();

        detailPresenter.favoriteAddRemove(imgFavorite);
    }

    public void displayDetails(String photoUrl, String title, String artist,
                               String description, String locationNotes, String submittedBy) {
//        Set text to fields
        tvImageTitle.setText(title);
        tvArtistName.setText(artist);
        tvDescription.setText(description);
        tvLocationNotes.setText(locationNotes);
        String submittedByString = getString(R.string.submitted_by) + submittedBy;
        tvSubmittedBy.setText(submittedByString);

//        Load image
        Glide.with(this)
                .load(photoUrl)
                .into(imgArtwork);

//        Check if field is empty and set visibility to gone.
        detailPresenter.nullDisplay(tvImageTitle, tvArtistName, tvDescription,
                tvLocationNotes, tvDescriptionLabel, viewDivider1, viewDivider2);
    }

    public void favoriteIconFill(boolean favorite){
//        Set favorite image
        if (favorite){
            imgFavorite.setImageResource(R.drawable.heart_selected_icon_3x);
        } else {
            imgFavorite.setImageResource(R.drawable.heart_icon_3x);
        }
    }

}
