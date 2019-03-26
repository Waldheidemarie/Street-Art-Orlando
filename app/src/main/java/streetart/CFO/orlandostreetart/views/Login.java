package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.Auth;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;

/**
 * Created by Eric on 3/21/2019.
 */
public class Login extends AppCompatActivity {

    @BindView(R.id.edit_email)
    EditText etEmail;
    @BindView(R.id.edit_password)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private static final String TAG = "Login";
    PreferenceManager preferenceManager = new PreferenceManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLogin(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    public void postLogin(String email, String password) {//
        Call<Auth> call = SERVICE.getUserAuthKey(email, password);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                assert response.body() != null;
                if (!response.body().getAuthToken().equals(""))
            preferenceManager.saveAuthBoolean(true);
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
