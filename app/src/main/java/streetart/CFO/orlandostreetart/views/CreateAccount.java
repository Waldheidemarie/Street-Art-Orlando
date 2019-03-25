package streetart.CFO.orlandostreetart.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.CreateAccountPresenter;

/**
 * Created by Eric on 3/13/2019.
 */
public class CreateAccount extends AppCompatActivity {

    @BindView(R.id.button_create_account)
    Button btnCreateAccount;
    @BindView(R.id.edit_email)
    EditText etEmail;
    @BindView(R.id.edit_email_confirm)
    EditText etEmailConfirm;
    @BindView(R.id.edit_nickname)
    EditText etNickName;
    @BindView(R.id.edit_password)
    EditText etPassword;

    private static final String TAG = "CreateAccount";
    CreateAccountPresenter presenter = new CreateAccountPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        ButterKnife.bind(this);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String emailConfirm = etEmailConfirm.getText().toString();
                presenter.checkValidEmail(email, emailConfirm);

                String nickName = etNickName.getText().toString();
                String password = etPassword.getText().toString();
                presenter.postRegisterUser();
            }
        });
    }
}
