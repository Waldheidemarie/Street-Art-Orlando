package streetart.CFO.orlandostreetart.presenters.FragmentPresenters;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;
import streetart.CFO.orlandostreetart.views.FragmentViews.ExploreFragment;

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
        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

        Call<GetSubmissions> call = service.getSubmissions();
        call.enqueue(new Callback<GetSubmissions>() {
            @Override
            public void onResponse(Call<GetSubmissions> call, Response<GetSubmissions> response) {
                Log.i(TAG, "onResponse: " );
                view.setupAdapter(response.body());
            }

            @Override
            public void onFailure(Call<GetSubmissions> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

}
