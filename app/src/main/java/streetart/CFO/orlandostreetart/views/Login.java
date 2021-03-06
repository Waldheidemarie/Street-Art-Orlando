package streetart.CFO.orlandostreetart.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.LoginPresenter;

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
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    private static final String TAG = "Login";
    LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        setTitle(R.string.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginPresenter.postLogin(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(v.getContext(), ForgotPassword.class);
                startActivity(forgotPassword);
            }
        });
    }
}
