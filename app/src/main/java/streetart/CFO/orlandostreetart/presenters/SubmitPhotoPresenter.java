package streetart.CFO.orlandostreetart.presenters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        submitPhotoView.startActivityForResult(cameraIntent, 2);


//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        String outPath = context.getString(R.string.sd_card) + "photo.jpeg";
//        File outFile = new File(outPath);
//
//        mCameraFileName = outFile.toString();
//        Uri art = Uri.parse(mCameraFileName);
////        Uri outUri = Uri.fromFile(outFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, art);
//
//        submitPhotoView.startActivityForResult(intent, 2);
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

    public void getArtInfo(String title, String artist, String location_note, String description, Uri art) {
        this.title = title;
        this.artist = artist;
        this.description = "test desc";
        this.location_note = "null loc";

//        todo test: missing photo
//                Convert image into base 64
//        convertBase64(art);
        postSubmissionCreate();
    }


    public void convertBase64(Uri art){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(submitPhotoView.getContentResolver(), art);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 25, outputStream);
            byte[] byteArray = outputStream.toByteArray();

            photo = "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getLongLat(Uri artUri){
        InputStream in = null;
        try {
            in = submitPhotoView.getContentResolver().openInputStream(artUri);
            ExifInterface exifInterface = new ExifInterface(in);
            latitude = (double) convertRationalLatLonToFloat(exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE),
                    exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF));
            longitude = (double) convertRationalLatLonToFloat(exifInterface.getAttribute
                            (ExifInterface.TAG_GPS_LONGITUDE),
                    exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF));
//            Refresh map
            SupportMapFragment mapFragment = (SupportMapFragment) submitPhotoView.getSupportFragmentManager()
                    .findFragmentById(R.id.mapViewFragment);
            mapFragment.getMapAsync(this);
            // Now you can extract any Exif tag you want
            // Assuming the image is a JPEG or supported raw format
        } catch (IOException e) {
            // Handle any errors
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {}
            }
        }
    }

    private static float convertRationalLatLonToFloat(
            String rationalString, String ref) {
        try {
            String [] parts = rationalString.split(",");
            String [] pair;
            pair = parts[0].split("/");
            double degrees = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            pair = parts[1].split("/");
            double minutes = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            pair = parts[2].split("/");
            double seconds = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
            if ((ref.equals("S") || ref.equals("W"))) {
                return (float) -result;
            }
            return (float) result;
        } catch (NumberFormatException e) {
            // Some of the nubmers are not valid
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            // Some of the rational does not follow the correct format
            throw new IllegalArgumentException();
        }
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
