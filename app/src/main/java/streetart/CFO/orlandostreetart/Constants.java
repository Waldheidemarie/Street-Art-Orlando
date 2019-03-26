package streetart.CFO.orlandostreetart;

import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;

/**
 * Created by Eric on 3/25/2019.
 */
public class Constants {

    public static final GetNetworkData SERVICE = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

    public static final String AUTHTOKEN = "authToken";
    public static final String SHARED_PREFS = "sharedPrefs";
}
