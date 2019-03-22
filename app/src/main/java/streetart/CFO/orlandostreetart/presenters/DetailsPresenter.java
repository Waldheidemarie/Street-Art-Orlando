package streetart.CFO.orlandostreetart.presenters;

import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.Details;

/**
 * Created by Eric on 3/15/2019.
 */
public class DetailsPresenter implements OnMapReadyCallback {

    private String title;
    private String artist;
    private String description;
    private String locationNotes;
    private Double latitude;
    private Double longitude;

    private static final String TAG = "DetailsPresenter";
    private Details view;
    private GoogleMap mMap;

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
       latitude = artDetails.getLatitude();
       longitude = artDetails.getLongitude();
//
////       Display details in view
        view.displayDetails(photoUrl, title, artist, description, locationNotes);
    }

    public void nullDisplay(TextView tvImageTitle, TextView tvArtistName,
                            TextView tvDescription, TextView tvLocationNotes,
                            TextView tvDescriptionLabel, View viewDivider1, View viewDivider2){
        if (title == null || title.equals("")){
            tvImageTitle.setVisibility(View.GONE);
            viewDivider1.setVisibility(View.GONE);
        }
        if (artist == null || artist.equals("")){
            tvArtistName.setVisibility(View.GONE);
            viewDivider2.setVisibility(View.GONE);
        }
        if (description == null || description.equals("")){
            tvDescription.setVisibility(View.GONE);
            tvDescriptionLabel.setVisibility(View.GONE);
        }
        if (locationNotes == null || locationNotes.equals("")){
            tvLocationNotes.setVisibility(View.GONE);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setIndoorEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        //seattle coordinates
        LatLng seattle = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(seattle));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seattle, 15));
    }
}
