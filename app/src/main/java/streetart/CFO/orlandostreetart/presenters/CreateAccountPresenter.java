package streetart.CFO.orlandostreetart.presenters;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import streetart.CFO.orlandostreetart.views.CreateAccount;

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
        if (email.equals("")){
            etEmail.setError("No email entered");
            return false;
        }
        if (emailConfirm.equals("")){
            etEmailConfirm.setError("No confirmation email entered");
            return false;
        }
        if (nickname.equals("")){
            etNickName.setError("No nickname entered");
            return false;
        }
        if (password.equals("")){
            etPassword.setError("No password entered");
            return false;
        }
        return true;
    }


    public boolean checkEmails(EditText etEmail, EditText etEmailConfirm) {
//        Check for valid email
        if (!isValidEmail(email)){
            etEmail.setError("Email is not valid");
            return false;
        }
//        Check for matching emails
        if (!email.equals(emailConfirm)){
            etEmailConfirm.setError("Emails do not match");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void postRegisterUser() {

    }

}
