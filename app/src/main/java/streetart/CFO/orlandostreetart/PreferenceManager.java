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

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void saveAuthBoolean(Boolean authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(AUTHTOKEN, authToken);

        editor.apply();
    }

//    public void checkUserAuth() {
//        if (createAccount != null) {
//            SharedPreferences sharedPreferences = createAccount.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//
//            Log.i(TAG, "checkUserAuth: authToken" + sharedPreferences.getString(AUTHTOKEN, "No authToken"));
//        } else {
//            Log.i(TAG, "checkUserAuth: no shared prefs");
//        }
//    }

}
