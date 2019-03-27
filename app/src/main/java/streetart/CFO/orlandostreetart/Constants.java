package streetart.CFO.orlandostreetart;

import android.content.Context;

import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;

/**
 * Created by Eric on 3/25/2019.
 */
public class Constants {

    public static final GetNetworkData SERVICE = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

    static final String AUTH_TOKEN_BOOLEAN = "authTokenBoolean";
    static final String AUTH_TOKEN_STRING = "authTokenString";
    static final String SHARED_PREFS = "sharedPrefs";

    public static String GET_AUTH_TOKEN(Context context) {
        PreferenceManager preferenceManager = new PreferenceManager(context);
        return preferenceManager.getAuthToken();
    }
}
