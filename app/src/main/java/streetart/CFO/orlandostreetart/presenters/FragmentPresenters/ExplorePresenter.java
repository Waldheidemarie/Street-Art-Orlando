package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.GetModel;
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
        Call<GetModel> call = SERVICE.getSubmissions();
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                Log.i(TAG, "onResponse: " );
                view.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

}
