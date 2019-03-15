package streetart.CFO.orlandostreetart.presenters;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;
import streetart.CFO.orlandostreetart.views.ExploreFragment;

/**
 * Created by Eric on 3/14/2019.
 */
public class RecyclerViewPresenter {

    private static final String TAG = "RecyclerViewPresenter";
    private Call<GetSubmissions> call;
    ExploreFragment view;

    public RecyclerViewPresenter(ExploreFragment exploreFragment) {
        this.view = exploreFragment;
    }

    public void getData() {

        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

        call = service.getSubmissions();
        call.enqueue(new Callback<GetSubmissions>() {
            @Override
            public void onResponse(Call<GetSubmissions> call, Response<GetSubmissions> response) {
                view.setupAdapter(response.body());
                Log.i(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<GetSubmissions> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

}
