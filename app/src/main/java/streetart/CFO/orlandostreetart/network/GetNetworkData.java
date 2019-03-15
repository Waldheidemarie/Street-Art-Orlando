package streetart.CFO.orlandostreetart.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import streetart.CFO.orlandostreetart.models.GetSubmissions;

public interface GetNetworkData {

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
    "Authorization: {API-KEY}"})
    @GET("submissions?page=1&per_page=10&status=approved")
    Call<GetSubmissions> getSubmissions();

}
