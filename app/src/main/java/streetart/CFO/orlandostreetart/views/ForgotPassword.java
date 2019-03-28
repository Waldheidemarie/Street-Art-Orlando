package streetart.CFO.orlandostreetart.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.ForgotPasswordPresenter;

public class ForgotPassword extends AppCompatActivity {

    @BindView(R.id.edit_email)
    EditText etEmail;
    @BindView(R.id.tvHaveCode)
    TextView tvHaveCode;
    @BindView(R.id.btnSendCode)
    TextView btnSendCode;

    private static final String TAG = "ForgotPassword";
    ForgotPasswordPresenter forgotPasswordPresenter= new ForgotPasswordPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter.sendSecurityCode(etEmail.getText().toString());
            }
        });
    }
}
