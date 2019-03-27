package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.Favorites;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.views.FragmentViews.ExploreFragment;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/14/2019.
 */
public class ExplorePresenter {

    private static final String TAG = "ExplorePresenter";
    private ExploreFragment view;

    public ExplorePresenter(ExploreFragment exploreFragment) {
        this.view = exploreFragment;
    }

    public void getData() {
        Call<Favorites> call = SERVICE.getSubmissions();
        call.enqueue(new Callback<Favorites>() {
            @Override
            public void onResponse(Call<Favorites> call, Response<Favorites> response) {
                Log.i(TAG, "onResponse: " );
                view.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<Favorites> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

}
