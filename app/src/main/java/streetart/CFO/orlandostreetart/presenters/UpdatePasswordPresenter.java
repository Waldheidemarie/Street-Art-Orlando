package streetart.CFO.orlandostreetart.presenters;

import android.content.Intent;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.views.FragmentViews.MainActivity;
import streetart.CFO.orlandostreetart.views.UpdatePassword;

import static streetart.CFO.orlandostreetart.Constants.GET_AUTH_TOKEN;
import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/27/2019.
 */
public class UpdatePasswordPresenter {

    private static final String TAG = "UpdatePasswordPresenter";

    public UpdatePasswordPresenter() {
    }


    public void updatePassword(String newPassword, final UpdatePassword updatePassword) {
        Call<Auth> call = SERVICE.putUpdatePassword(GET_AUTH_TOKEN(updatePassword),newPassword);

        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()){
//                    Password is update.
                    Toast.makeText(updatePassword, "Password Updated", Toast.LENGTH_SHORT).show();
                    Intent returnSettings = new Intent(updatePassword, MainActivity.class);
                    updatePassword.startActivity(returnSettings);
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });
    }
}
