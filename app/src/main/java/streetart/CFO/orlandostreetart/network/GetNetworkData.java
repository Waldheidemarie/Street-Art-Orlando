package streetart.CFO.orlandostreetart.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import streetart.CFO.orlandostreetart.BuildConfig;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.models.Favorite;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.models.PostUserRegister;

public interface GetNetworkData {

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
    "Authorization: " + BuildConfig.API_KEY})
    @GET("submissions?status=approved")
    Call<GetSubmissions> getSubmissions();

    @POST("users/register")
    Call<PostUserRegister> postUserRegister(@Body PostUserRegister postRegister);

    @FormUrlEncoded
    @POST("authenticate")
    Call<Auth> getUserAuthKey(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("submissions/{id}/favorite")
    Call<Favorite> postAddFavorite(@Header("Authorization") String authToken,
            @Path("id") int submissionId);
}
