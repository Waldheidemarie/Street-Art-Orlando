package streetart.CFO.orlandostreetart;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static streetart.CFO.orlandostreetart.Constants.AUTHTOKEN;
import static streetart.CFO.orlandostreetart.Constants.SHARED_PREFS;

/**
 * Created by Eric on 3/25/2019.
 */
public class PreferenceManager {

    private Context context;

    private static final String TAG = "PreferenceManager";
//    AUTHTOKEN = TRUE (USER LOGGED IN) / FALSE = USER LOGGED OUT

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void saveAuthBoolean(Boolean authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(AUTHTOKEN, authToken);

        editor.apply();
    }

    public boolean checkUserAuth(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getBoolean(AUTHTOKEN, false);
    }

}
