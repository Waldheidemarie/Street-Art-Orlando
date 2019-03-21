package streetart.CFO.orlandostreetart.presenters;

import android.view.View;
import android.widget.TextView;

import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.Details;

/**
 * Created by Eric on 3/15/2019.
 */
public class DetailsPresenter  {

    private String title;
    private String artist;
    private String description;
    private String locationNotes;

    private static final String TAG = "DetailsPresenter";
    private Details view;

    public DetailsPresenter(Details details) {
        this.view = details;
    }

    public void getPassedDetails() {

        GetSubmissions.Submission artDetails = view.getIntent().getParcelableExtra("artDetails");

       String photoUrl = artDetails.getPhotoUrl();
       title = artDetails.getTitle();
       artist = artDetails.getArtist();
       description = artDetails.getDescription();
       locationNotes = artDetails.getLocationNote();
       Boolean favorite = artDetails.getFavorite();
       Double latitude = artDetails.getLatitude();
       Double longitude = artDetails.getLongitude();
//
////       Display details in view
        view.displayDetails(photoUrl, title, artist, description, locationNotes);
    }

    public void nullDisplay(TextView tvImageTitle, TextView tvArtistName,
                            TextView tvDescription, TextView tvLocationNotes,
                            TextView tvDescriptionLabel, View viewDivider1, View viewDivider2){
        if (title.equals("")){
            tvImageTitle.setVisibility(View.GONE);
            viewDivider1.setVisibility(View.GONE);
        }
        if (artist.equals("")){
            tvArtistName.setVisibility(View.GONE);
            viewDivider2.setVisibility(View.GONE);
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
