package streetart.CFO.orlandostreetart.views;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
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
    @BindView(R.id.layout_email)
    TextInputLayout emailLayout;
    @BindView(R.id.tvHaveCode)
    TextView tvHaveCode;
    @BindView(R.id.btnSendCode)
    TextView btnSendCode;
    @BindView(R.id.etPasswordLayout)
    TextInputLayout etPasswordLayout;
    @BindView(R.id.edit_password)
    EditText etPassword;
    @BindView(R.id.etSecurityTokenLayout)
    TextInputLayout etSecurityTokenLayout;
    @BindView(R.id.etSecurityToken)
    EditText etSecurityToken;

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

        if (getIntent().getBooleanExtra("haveKeyBoolean", false)){
            haveSecurityCode();
        }

        tvHaveCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haveSecurityCode();
            }
        });
    }

    private void haveSecurityCode(){
        etPasswordLayout.setVisibility(View.VISIBLE);
        etEmail.setText(getIntent().getStringExtra("email"));
        etSecurityTokenLayout.setVisibility(View.VISIBLE);
        btnSendCode.setText(getText(R.string.update_password));
        tvHaveCode.setVisibility(View.INVISIBLE);
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter.updatePassword(etEmail.getText().toString(), etSecurityToken.getText().toString(),
                        etPassword.getText().toString());
            }
        });
    }
}
