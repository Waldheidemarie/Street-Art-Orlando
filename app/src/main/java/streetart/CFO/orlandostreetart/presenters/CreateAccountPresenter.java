package streetart.CFO.orlandostreetart.presenters;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.models.PostUserRegister;
import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;
import streetart.CFO.orlandostreetart.views.CreateAccount;
import streetart.CFO.orlandostreetart.views.FragmentViews.MainActivity;

/**
 * Created by Eric on 3/25/2019.
 */
public class CreateAccountPresenter {

    private static final String TAG = "CreateAccountPresenter";
    CreateAccount view;
    String email;
    String emailConfirm;
    String nickname;
    String password;


    public CreateAccountPresenter(CreateAccount createAccount) {
        this.view = createAccount;
    }

    public void getStringInputs(EditText etEmail, EditText etEmailConfirm, EditText etNickName, EditText etPassword) {
        email = etEmail.getText().toString();
        emailConfirm = etEmailConfirm.getText().toString();
        nickname = etNickName.getText().toString();
        password = etPassword.getText().toString();
    }

    public Boolean checkForEmpty(EditText etEmail, EditText etEmailConfirm, EditText etNickName, EditText etPassword) {
        if (email.equals("")) {
            etEmail.setError("No email entered");
            return false;
        }
        if (emailConfirm.equals("")) {
            etEmailConfirm.setError("No confirmation email entered");
            return false;
        }
        if (nickname.equals("")) {
            etNickName.setError("No nickname entered");
            return false;
        }
        if (password.equals("")) {
            etPassword.setError("No password entered");
            return false;
        }
        return true;
    }


    public boolean checkEmails(EditText etEmail, EditText etEmailConfirm) {
//        Check for valid email
        if (!isValidEmail(email)) {
            etEmail.setError("Email is not valid");
            return false;
        }
//        Check for matching emails
        if (!email.equals(emailConfirm)) {
            etEmailConfirm.setError("Emails do not match");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void postRegisterUser() {
        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);
        PostUserRegister userRegister = new PostUserRegister(nickname, email, password);
        Log.i(TAG, "postRegisterUser: " + nickname + " " + email + " " + password);
        Call<PostUserRegister> call = service.postUserRegister(userRegister);

        call.enqueue(new Callback<PostUserRegister>() {
            @Override
            public void onResponse(Call<PostUserRegister> call, Response<PostUserRegister> response) {
                if (response.isSuccessful()) {
//                    Added user successfully
                    Toast.makeText(view, "Welcome " + response.body().getName(), Toast.LENGTH_SHORT).show();
                    Intent returnHome = new Intent(view, MainActivity.class);
                    view.startActivity(returnHome);
                } else {
//                    Error adding new user
                    try {
                        Toast.makeText(view, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PostUserRegister> call, Throwable t) {
                Toast.makeText(view, "Error :(", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: ");
            }
        });

    }

}
