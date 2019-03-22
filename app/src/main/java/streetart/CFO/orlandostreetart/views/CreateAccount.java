package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.models.GetSubmissions;
import streetart.CFO.orlandostreetart.models.PostRegister;
import streetart.CFO.orlandostreetart.network.GetNetworkData;
import streetart.CFO.orlandostreetart.network.RetroClient;

/**
 * Created by Eric on 3/13/2019.
 */
public class CreateAccount extends AppCompatActivity {

    @BindView(R.id.button_create_account)
    Button btnCreateAccount;

    private static final String TAG = "CreateAccount";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        ButterKnife.bind(this);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUser();
            }
        });
    }

    public void postUser() {
//        todo: Post new users
//        PostRegister register = new PostRegister("ericdgood@yahoo.com", "password");
//
//        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);
////
//        Call<Auth> call = service.createRegisterPost("ericdgood@yahoo.com", "password");
//        call.enqueue(new Callback<Auth>() {
//            @Override
//            public void onResponse(Call<Auth> call, Response<Auth> response) {
//                Auth postResopnse = response.body();
//                Log.i(TAG, "onResponse: " + response.code() + "\n" +
//                "auth:" + response.body().getAuthToken());
//            }
//
//            @Override
//            public void onFailure(Call<Auth> call, Throwable t) {
//                Log.i(TAG, "onFailure: ");
//            }
//        });
    }

}
