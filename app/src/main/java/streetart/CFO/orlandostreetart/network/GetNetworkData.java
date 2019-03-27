package streetart.CFO.orlandostreetart.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.models.GetModel;
import streetart.CFO.orlandostreetart.models.PostUserRegister;

import static streetart.CFO.orlandostreetart.BuildConfig.API_KEY;

public interface GetNetworkData {

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
    "Authorization: " + API_KEY})
    @GET("submissions?status=approved")
    Call<GetModel> getSubmissions();

    @POST("users/register")
    Call<PostUserRegister> postUserRegister(@Body PostUserRegister postRegister);

    @FormUrlEncoded
    @POST("authenticate")
    Call<Auth> getUserAuthKey(
            @Field("email") String email,
            @Field("password") String password);

    @POST("submissions/{id}/favorite")
    Call<GetModel> postAddFavorite(@Header("Authorization") String API_KEY,
                                   @Path("id") int submissionId);

    @DELETE("submissions/{id}/unfavorite")
    Call<GetModel> postDeleteFavorite(@Header("Authorization") String API_KEY,
                                   @Path("id") int submissionId);

    @GET("submissions/favorites")
    Call<GetModel> getFavorites(@Header("Authorization") String API_KEY);

}
