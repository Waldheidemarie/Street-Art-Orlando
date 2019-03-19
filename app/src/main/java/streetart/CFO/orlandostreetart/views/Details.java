package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.DetailsPresenter;

/**
 * Created by Eric on 3/15/2019.
 */
public class Details extends AppCompatActivity {

    @BindView(R.id.imgArtwork)
    ImageView imgArtwork;

    private static final String TAG = "Details";
    DetailsPresenter detailPresenter = new DetailsPresenter(this);

    public Details() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ButterKnife.bind(this);

        detailPresenter.getPassedDetails();
    }

    public void displayDetails(String photoUrl) {
        Glide.with(this)
                .load(photoUrl)
                .into(imgArtwork);
    }
}
