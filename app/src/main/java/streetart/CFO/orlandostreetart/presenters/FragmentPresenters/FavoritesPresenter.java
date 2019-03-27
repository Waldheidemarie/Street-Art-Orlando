package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.app.Activity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.Favorites;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.FragmentViews.FavoritesFragment;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

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
        PreferenceManager preferenceManager = new PreferenceManager(favoriteView.getActivity());
        Call<Favorites> call = SERVICE.getFavorites(preferenceManager.getAuthToken());
        call.enqueue(new Callback<Favorites>() {
            @Override
            public void onResponse(Call<Favorites> call, Response<Favorites> response) {
                Log.i(TAG, "onResponse: " );
                favoriteView.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<Favorites> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
