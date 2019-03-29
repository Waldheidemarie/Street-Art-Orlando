package streetart.CFO.orlandostreetart.presenters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.ForgotPassword;
import streetart.CFO.orlandostreetart.views.FragmentViews.MainActivity;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/27/2019.
 */
public class ForgotPasswordPresenter {

    private static final String TAG = "ForgotPasswordPresenter";
    private final Context context;

    public ForgotPasswordPresenter(Context context) {
        this.context = context;
    }


    public void sendSecurityCode(final String email) {
        Call<Auth> call = SERVICE.postPasswordForget(email);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: ");
                    PreferenceManager preferenceManager = new PreferenceManager(context);
                    preferenceManager.saveAuthBoolean(false,"",email);
                    codeSentAlert();
                } else {
//                  Show error message if incorrect info is entered.
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(context, error.error(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(context, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void codeSentAlert(){
        new AlertDialog.Builder(context)
                .setTitle("Security Code Sent")
                .setMessage("A security code will be sent to you via email shortly. The code will expire in 4 hours")

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent haveKey = new Intent(context, ForgotPassword.class);
                        haveKey.putExtra("haveKeyBoolean", true);
                        context.startActivity(haveKey);
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void updatePassword(final String securityCode, final String etNewPassword) {
        PreferenceManager preferenceManager = new PreferenceManager(context);
        Call<Auth> call = SERVICE.postPasswordReset(preferenceManager.getUserEmail(), securityCode, etNewPassword);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Password Updated", Toast.LENGTH_SHORT).show();
                    getAuthToken(etNewPassword);
                } else {
//                  Show error message if incorrect info is entered.
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(context, error.error(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(context, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAuthToken(String etNewPassword) {
        final PreferenceManager preferenceManager = new PreferenceManager(context);

        Call<Auth> call = SERVICE.postUserAuthKey(preferenceManager.getUserEmail(), etNewPassword);

        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                assert response.body() != null;
                if (!response.body().getAuthToken().equals(""))
                    preferenceManager.saveAuthBoolean(true, response.body().getAuthToken(), preferenceManager.getUserEmail());
                Intent returnHome = new Intent(context, MainActivity.class);
                context.startActivity(returnHome);
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.i(TAG, "onFailure: GET_AUTH_TOKEN");
            }
        });
    }
}
