package streetart.CFO.orlandostreetart.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.SubmitPhoto;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/30/2019.
 */
public class SubmitPhotoPresenter extends Activity{

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

    public SubmitPhotoPresenter(Context context, SubmitPhoto submitPhoto) {
        this.submitPhotoView = submitPhoto;
        this.context = context;
    }

    public void getArtInfo(String title, String artist, String location_note, String description, double latitude, double longitude) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.location_note = location_note;
        this.latitude = latitude;
        this.longitude = longitude;

//        todo test: missing photo
        photo = "data:image/jpeg;base64,starts/base/64/string/here";
        postSubmissionCreate();
    }

    public void postSubmissionCreate() {
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
