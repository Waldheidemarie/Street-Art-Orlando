package streetart.CFO.orlandostreetart;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static streetart.CFO.orlandostreetart.Constants.AUTH_TOKEN_BOOLEAN;
import static streetart.CFO.orlandostreetart.Constants.AUTH_TOKEN_STRING;
import static streetart.CFO.orlandostreetart.Constants.SHARED_PREFS;
import static streetart.CFO.orlandostreetart.Constants.USER_EMAIL;

/**
 * Created by Eric on 3/25/2019.
 */
public class PreferenceManager {

    private Context context;

    private static final String TAG = "PreferenceManager";
//    AUTH_TOKEN_BOOLEAN = TRUE (USER LOGGED IN) / FALSE = USER LOGGED OUT

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void saveAuthBoolean(Boolean authTokenBoolean, String authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(AUTH_TOKEN_STRING, authToken);
        editor.putBoolean(AUTH_TOKEN_BOOLEAN, authTokenBoolean);

        editor.apply();
    }

    public boolean checkUserAuth(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getBoolean(AUTH_TOKEN_BOOLEAN, false);
    }

    public String getAuthToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(AUTH_TOKEN_STRING,"");
    }

}
