package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.views.CreateAccount;
import streetart.CFO.orlandostreetart.views.Login;
import streetart.CFO.orlandostreetart.views.UpdatePassword;

/**
 * Created by Eric on 3/13/2019.
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.button_create_account)
    Button btnCreateAccount;
    @BindView(R.id.button_update_password)
    Button btnUpdatePassword;
    @BindView(R.id.button_login)
    Button btnLogInLogOut;
    @BindView(R.id.layout_update_password)
    RelativeLayout layUpdatePassword;

    private static final String TAG = "SettingsFragment";

    public SettingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);

        PreferenceManager preferenceManager = new PreferenceManager(getActivity());
        if(preferenceManager.checkUserAuth()){
            userLoggedIn();
        } else {
            userLoggedOut();
        }

        return view;
    }

    private void userLoggedOut() {

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccount = new Intent(getActivity(), CreateAccount.class);
                startActivity(createAccount);
            }
        });


        btnLogInLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getActivity(), Login.class);
                startActivity(login);
            }
        });
    }

    private void userLoggedIn(){
        btnCreateAccount.setVisibility(View.INVISIBLE);
        layUpdatePassword.setVisibility(View.VISIBLE);
        btnLogInLogOut.setText(R.string.logout);

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updatePassword = new Intent(getActivity(), UpdatePassword.class);
                startActivity(updatePassword);
            }
        });

        btnLogInLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log out user.

            }
        });

    }
}
