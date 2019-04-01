package streetart.CFO.orlandostreetart.presenters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.SubmitPhoto;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/30/2019.
 */
public class SubmitPhotoPresenter implements OnMapReadyCallback {

    private static final String TAG = "SubmitPhotoPresenter";
    private Context context;
    private SubmitPhoto submitPhotoView;
    private String mCameraFileName;

    //    Submission Create Parameters
    private String photo;
    private String title;
    private String artist;
    private String description;
    private Double latitude;
    private Double longitude;
    private String location_note;

    private String[] GALLERY_PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String[] CAMERA_PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };
    private String[] LOCATION_PERMISSIONS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public SubmitPhotoPresenter(Context context, SubmitPhoto submitPhoto) {
        this.context = context;
        this.submitPhotoView = submitPhoto;
    }

    public void locationPermissions()    {
        int PERMISSION_LOCATION = 3;
        ActivityCompat.requestPermissions(submitPhotoView,
                LOCATION_PERMISSIONS,
                PERMISSION_LOCATION);
    }

    public void galleryPermissions()    {
        //    Camera Permissions
        int PERMISSION_GALLERY = 1;
        ActivityCompat.requestPermissions(submitPhotoView,
                GALLERY_PERMISSIONS,
                PERMISSION_GALLERY);
    }

    public void cameraPermissions()    {
        int PERMISSION_CAMERA = 2;
        ActivityCompat.requestPermissions(submitPhotoView,
                CAMERA_PERMISSIONS,
                PERMISSION_CAMERA);
    }
    public void galleryImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        submitPhotoView.startActivityForResult(intent, 1);
    }

//    todo: camera image not showing
    public void cameraImage() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        String outPath = context.getString(R.string.sd_card) + "photo.jpeg";
        File outFile = new File(outPath);

        mCameraFileName = outFile.toString();
        Uri art = Uri.parse(mCameraFileName);
        Uri outUri = Uri.fromFile(outFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, art);

        submitPhotoView.startActivityForResult(intent, 2);
    }

    public void getLocation() {
        LocationManager lm = (LocationManager) submitPhotoView.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) submitPhotoView.getSupportFragmentManager()
                .findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        mMap.setIndoorEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        //User coordinates
        LatLng User = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(User));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(User, 15));
    }

    public void getArtInfo(String title, String artist, String location_note, String description,
                           double latitude, double longitude, Uri art) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.location_note = location_note;
        this.latitude = latitude;
        this.longitude = longitude;

//        todo test: missing photo
        photo = String.valueOf(art);
        postSubmissionCreate();
    }

    private void postSubmissionCreate() {
        final PreferenceManager preferenceManager = new PreferenceManager(context);
        Call<Auth> call = SERVICE.postSubmissionCreate(preferenceManager.getAuthToken(),
                photo, title, artist, description ,latitude, longitude, location_note);

        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(context, "Art submitted", Toast.LENGTH_SHORT).show();
                } else {
                    assert response.errorBody() != null;
                    Log.i(TAG, "onResponse: " + response.errorBody().toString());
//                  Show error message if incorrect info is entered.
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(context, error.error(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(context, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
