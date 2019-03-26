package streetart.CFO.orlandostreetart.presenters;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.FragmentViews.MainActivity;
import streetart.CFO.orlandostreetart.views.Login;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/26/2019.
 */
public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private Login loginView;
    private PreferenceManager preferenceManager = new PreferenceManager(loginView);

    public LoginPresenter(Login login) {
        this.loginView = login;
    }


    public void postLogin(String email, String password) {//
        Call<Auth> call = SERVICE.getUserAuthKey(email, password);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {

                if (response.isSuccessful()) {
                    preferenceManager.saveAuthBoolean(true);
                    Intent returnMain = new Intent(loginView, MainActivity.class);
                    loginView.startActivity(returnMain);
                } else {
//                  Show error message if incorrect info is entered.
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(loginView, error.error(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(loginView, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
