package streetart.CFO.orlandostreetart.views.FragmentViews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.views.CreateAccount;
import streetart.CFO.orlandostreetart.views.Login;
import streetart.CFO.orlandostreetart.views.MyPhotos;
import streetart.CFO.orlandostreetart.views.SubmitPhoto;
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
    @BindView(R.id.text_create_account_message)
    TextView tvCreateAccountMessage;
    @BindView(R.id.button_submit_photo)
    Button btnSubmitPhoto;
    @BindView(R.id.button_my_photos)
    Button btnMyPhotos;
    @BindView(R.id.button_terms_conditions)
    Button btnTermsConditions;
    @BindView(R.id.button_privacy)
    Button btnPrivacy;
    @BindView(R.id.button_community_guidelines)
    Button btnGuidelines;

    private static final String TAG = "SettingsFragment";

    public SettingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);

        termsAndConditions();

        PreferenceManager preferenceManager = new PreferenceManager(getActivity());
        if(preferenceManager.checkUserAuth()){
            userLoggedIn(preferenceManager);
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
        
        btnSubmitPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToSubmitPhoto(getString(R.string.logged_in_submit_photo));
            }
        });

        btnMyPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToSubmitPhoto(getString(R.string.logged_in_your_photo));
            }
        });
    }

    private void userLoggedIn(final PreferenceManager preferenceManager){
        tvCreateAccountMessage.setVisibility(View.INVISIBLE);
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
                logoutAlertDialog(preferenceManager);
            }
        });
        
        btnSubmitPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Go to photo submit activity
                Intent submitPhoto = new Intent(getActivity(), SubmitPhoto.class);
                startActivity(submitPhoto);
            }
        });

        btnMyPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Go to my Photos
                Intent myPhotos = new Intent(getActivity(), MyPhotos.class);
                startActivity(myPhotos);
            }
        });
    }

    private void logoutAlertDialog(final PreferenceManager preferenceManager){
        new AlertDialog.Builder(getActivity())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        preferenceManager.saveAuthBoolean(false, "", null);
                        Intent returnSettings = new Intent(getActivity(), MainActivity.class);
                        startActivity(returnSettings);
                        Toast.makeText(getActivity(), "You are now logged out", Toast.LENGTH_SHORT).show();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void loginToSubmitPhoto(String message){
        new AlertDialog.Builder(getActivity())
                .setTitle("Login")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void termsAndConditions(){
        btnTermsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://streetartorlando.com/terms"));
                startActivity(browserIntent);
            }
        });

        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://streetartorlando.com/privacy"));
                startActivity(browserIntent);
            }
        });

        btnGuidelines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://streetartorlando.com/community-guidelines"));
                startActivity(browserIntent);
            }
        });
    }
}
