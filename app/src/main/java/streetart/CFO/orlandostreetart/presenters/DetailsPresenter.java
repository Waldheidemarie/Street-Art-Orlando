package streetart.CFO.orlandostreetart.presenters;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.Details;

/**
 * Created by Eric on 3/15/2019.
 */
public class DetailsPresenter  {

    String title;
    String artist;
    String description;
    String locationNotes;

    private static final String TAG = "DetailsPresenter";
    private Details view;

    public DetailsPresenter(Details details) {
        this.view = details;
    }

    public void getPassedDetails() {
       String photoUrl = view.getIntent().getStringExtra("photoUrl");
       title = view.getIntent().getStringExtra("title");
       artist = view.getIntent().getStringExtra("artist");
       description = view.getIntent().getStringExtra("description");
       locationNotes = view.getIntent().getStringExtra("locationNotes");
       String favorite = view.getIntent().getStringExtra("favorite");
       String latitude = view.getIntent().getStringExtra("latitude");
       String longitude = view.getIntent().getStringExtra("longitude");

//       Display details in view
        view.displayDetails(photoUrl, title, artist, description, locationNotes);
    }

    public void nullDisplay(TextView tvImageTitle, TextView tvArtistName, TextView tvDescription, TextView tvLocationNotes, TextView tvDescriptionLabel){
        if (title.equals("")){
            tvImageTitle.setVisibility(View.GONE);
        }
        if (artist.equals("")){
            tvArtistName.setVisibility(View.GONE);
        }
        if (description.equals("")){
            tvDescription.setVisibility(View.GONE);
            tvDescriptionLabel.setVisibility(View.GONE);
        }
        if (locationNotes.equals("")){
            tvLocationNotes.setVisibility(View.GONE);
        }
    }
}
