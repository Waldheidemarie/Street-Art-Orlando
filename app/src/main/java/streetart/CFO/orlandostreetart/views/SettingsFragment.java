package streetart.CFO.orlandostreetart.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.SettingsPresenter;

/**
 * Created by Eric on 3/13/2019.
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.button_create_account)
    Button btnCreateAccount;
    @BindView(R.id.button_update_password)
    Button btnUpdatePassword;

    private static final String TAG = "SettingsFragment";
    SettingsPresenter settingsPresentor = new SettingsPresenter(this);

    public SettingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);

       btnCreateAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent createAccount = new Intent(getActivity(), CreateAccount.class);
               startActivity(createAccount);
           }
       });

       btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent updatePassword = new Intent(getActivity(), UpdatePassword.class);
               startActivity(updatePassword);
           }
       });



        return view;
    }
}
