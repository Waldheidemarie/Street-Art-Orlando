package streetart.CFO.orlandostreetart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eric on 3/21/2019.
 */
public class Auth {

    @SerializedName("auth_token")
    @Expose
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
