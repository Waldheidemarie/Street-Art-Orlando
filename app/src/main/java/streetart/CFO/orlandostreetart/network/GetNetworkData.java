package streetart.CFO.orlandostreetart.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import streetart.CFO.orlandostreetart.BuildConfig;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.models.PostRegister;

public interface GetNetworkData {

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
    "Authorization: " + BuildConfig.API_KEY})
    @GET("submissions?status=approved")
    Call<GetSubmissions> getSubmissions();

    @FormUrlEncoded
    @POST("users/register")
    Call<PostRegister> postUserRegister(@Body PostRegister postRegister);

    @FormUrlEncoded
    @POST("authenticate")
    Call<Auth> createRegisterPost(
            @Field("email") String email,
            @Field("password") String password
    );
}
