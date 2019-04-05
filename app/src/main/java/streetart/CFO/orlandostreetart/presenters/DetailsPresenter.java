package streetart.CFO.orlandostreetart.presenters;

import android.graphics.Bitmap;
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
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.adapters.RecyclerViewAdapter;
import streetart.CFO.orlandostreetart.models.GetModel;
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
    private String submittedBy;
    private boolean favorite;

    private static final String TAG = "DetailsPresenter";
    private Details detailView;
    private GoogleMap mMap;
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(null,null,null);

    public DetailsPresenter(Details details) {
        this.detailView = details;
    }

    public void getPassedDetails() {

        GetModel.Submission artDetails = detailView.getIntent().getParcelableExtra("artDetails");

       String photoUrl = artDetails.getPhotoUrl();
       id = artDetails.getId();
       title = artDetails.getTitle();
       artist = artDetails.getArtist();
       description = artDetails.getDescription();
       locationNotes = artDetails.getLocationNote();
       latitude = artDetails.getLatitude();
       longitude = artDetails.getLongitude();
       submittedBy = artDetails.getNickname();

       if (artDetails.getFavorite() == null){
           favorite = false;}
           else{
           favorite = artDetails.getFavorite();
           detailView.favoriteIconFill(favorite);
       }

//       Display details in detailView
        detailView.displayDetails(photoUrl, title, artist, description, locationNotes, submittedBy);
        detailView.favoriteIconFill(favorite);
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

        //User coordinates
        LatLng User = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(User));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(User, 15));
        detailView.mapView.onResume();

//        Log.i(TAG, "onMapReady: start");
//        final ImageView mapPreview = detailView.findViewById(R.id.mapViewFragment);
//
//        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
//            @Override
//            public void onMapLoaded() {
//                // Make a snapshot when map's done loading
//                mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
//                    @Override
//                    public void onSnapshotReady(Bitmap bitmap) {
//                        mapPreview.setImageBitmap(bitmap);
//                        Log.i(TAG, "onSnapshotReady: ");
//                        // If map won't be used afterwards, remove it's views
////              ((FrameLayout)findViewById(R.id.map)).removeAllViews();
//                    }
//                });
//            }
//        });
//        mMap.setIndoorEnabled(true);
//        UiSettings uiSettings = mMap.getUiSettings();
//        uiSettings.setZoomControlsEnabled(true);
//
        //User coordinates
//        LatLng User = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(User));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(User, 18));
//        Log.i(TAG, "onMapReady: ");
    }
    
//TODO: having issues with adding and deleting
    public void favoriteAddRemove(final ImageView imgFavorite) {
        final PreferenceManager preferenceManager = new PreferenceManager(detailView);

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!preferenceManager.checkUserAuth()) {
//                    User is not logged in
                    Toast.makeText(detailView, R.string.login_required_to_save_favorites, Toast.LENGTH_SHORT).show();
                } else {
//                  User is logged in
                    if (favorite){
                        favorite = false;
                        Call<GetModel> call = SERVICE.deleteDeleteFavorite(preferenceManager.getAuthToken(), id);
                        callFavorite(call);
                        detailView.favoriteIconFill(favorite);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(detailView, "Removed from favorites", Toast.LENGTH_SHORT).show();
                    }else{
                        favorite = true;
                        Call<GetModel> call = SERVICE.postAddFavorite(preferenceManager.getAuthToken(), id);
                        callFavorite(call);
                        detailView.favoriteIconFill(favorite);
                        Toast.makeText(detailView, "Added to favorites", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void callFavorite(Call<GetModel> call) {
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.code());
                } else {
                    Toast.makeText(detailView, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Log.i(TAG, "onFailure: favoriteAdd/Remove");
            }
        });
    }
}
