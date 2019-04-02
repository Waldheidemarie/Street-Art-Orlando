package streetart.CFO.orlandostreetart.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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
    @BindView(R.id.switch_agree_terms)
    Switch swAgreeTerms;
    @BindView(R.id.button_agree_terms)
    Button btnAgreeTerms;

    private static final String TAG = "CreateAccount";
    CreateAccountPresenter presenter = new CreateAccountPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        ButterKnife.bind(this);

        setTitle(R.string.create_account);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.getStringInputs(etEmail,etEmailConfirm,etNickName,etPassword);

                if (presenter.checkForEmpty(etEmail,etEmailConfirm,etNickName,etPassword,swAgreeTerms, btnAgreeTerms) &&
                presenter.checkEmails(etEmail, etEmailConfirm)) {
//              Do this if all fields are filled and emails are valid
                    presenter.postRegisterUser();
                }
            }
        });
    }
}
