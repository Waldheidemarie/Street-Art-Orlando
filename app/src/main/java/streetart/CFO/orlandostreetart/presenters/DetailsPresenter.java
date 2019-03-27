package streetart.CFO.orlandostreetart.presenters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.models.Favorite;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.Details;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/15/2019.
 */
public class DetailsPresenter implements OnMapReadyCallback {

    private int id;
    private String title;
    private String artist;
    private String description;
    private String locationNotes;
    private Double latitude;
    private Double longitude;

    private static final String TAG = "DetailsPresenter";
    private Details detailView;
    private GoogleMap mMap;

    public DetailsPresenter(Details details) {
        this.detailView = details;
    }

    public void getPassedDetails() {

        GetSubmissions.Submission artDetails = detailView.getIntent().getParcelableExtra("artDetails");

       String photoUrl = artDetails.getPhotoUrl();
       id = artDetails.getId();
       title = artDetails.getTitle();
       artist = artDetails.getArtist();
       description = artDetails.getDescription();
       locationNotes = artDetails.getLocationNote();
       Boolean favorite = artDetails.getFavorite();
       latitude = artDetails.getLatitude();
       longitude = artDetails.getLongitude();
//
////       Display details in detailView
        detailView.displayDetails(photoUrl, title, artist, description, locationNotes);
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

    public void favoriteAddRemove(ImageView imgFavorite) {
        final PreferenceManager preferenceManager = new PreferenceManager(detailView);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: favoriteAdd" + id);
                Call<Favorite> call = SERVICE.postAddFavorite(preferenceManager.getAuthToken(), id);
                call.enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                        if (response.isSuccessful()){
                            Log.i(TAG, "onResponse: " + response.code());
                            Toast.makeText(detailView, "Added to favorites", Toast.LENGTH_SHORT).show();
                        }else {
                            APIError error = ErrorUtils.parseError(response);
                            Toast.makeText(detailView, error.error(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        Log.i(TAG, "onFailure: favoriteAdd/Remove");
                    }
                });
            }
        });
    }
}
