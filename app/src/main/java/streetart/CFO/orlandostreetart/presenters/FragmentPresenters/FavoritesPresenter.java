package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.GetModel;
import streetart.CFO.orlandostreetart.views.FragmentViews.FavoritesFragment;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;
import static streetart.CFO.orlandostreetart.Constants.GET_AUTH_TOKEN;

/**
 * Created by Eric on 3/26/2019.
 */
public class FavoritesPresenter {

    private static final String TAG = "FavoritesPresenter";
    private FavoritesFragment favoriteView;

    public FavoritesPresenter(FavoritesFragment favoritesFragment) {
        this.favoriteView = favoritesFragment;
    }

    public void getData() {
        Call<GetModel> call = SERVICE.getFavorites(GET_AUTH_TOKEN(favoriteView.getActivity()));
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                Log.i(TAG, "onResponse: " );
                favoriteView.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
