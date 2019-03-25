package streetart.CFO.orlandostreetart.presenters;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import streetart.CFO.orlandostreetart.views.CreateAccount;

/**
 * Created by Eric on 3/25/2019.
 */
public class CreateAccountPresenter {

    private static final String TAG = "CreateAccountPresenter";
    CreateAccount view;

    public CreateAccountPresenter(CreateAccount createAccount) {
        this.view = createAccount;
    }

    public void postRegisterUser() {



    }

    public void checkValidEmail(String email, String emailConfirm) {
        if (isValidEmail(email) && email.equals(emailConfirm)){
            Toast.makeText(view, "Valid Email", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view, "Please Check Email", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
