package streetart.CFO.orlandostreetart.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.GetSubmissions;

public interface GetNetworkData {

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
    "Authorization: " + R.string.API_KEY})
    @GET("submissions?status=approved")
    Call<GetSubmissions> getSubmissions();

}
