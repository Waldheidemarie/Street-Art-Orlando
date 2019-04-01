package streetart.CFO.orlandostreetart.presenters;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.GetModel;
import streetart.CFO.orlandostreetart.views.MyPhotos;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 4/1/2019.
 */
public class MyPhotosPresenter {

    private static final String TAG = "MyPhotosPresenter";
    private MyPhotos myPhotosView;

    public MyPhotosPresenter(MyPhotos myPhotos) {
        this.myPhotosView = myPhotos;
    }


    public void getMyPhotos() {
        PreferenceManager preferenceManager = new PreferenceManager(myPhotosView);
        Call<GetModel> call = SERVICE.getMyPhotos(preferenceManager.getAuthToken());
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                myPhotosView.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
