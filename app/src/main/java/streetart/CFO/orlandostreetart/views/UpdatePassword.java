package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.UpdatePasswordPresenter;

/**
 * Created by Eric on 3/14/2019.
 */
public class UpdatePassword extends AppCompatActivity {

    @BindView(R.id.btnUpdatePassword)
    Button btnUpdatePassword;
    @BindView(R.id.edit_update_password)
    EditText etUpdatePassword;

    UpdatePasswordPresenter updatePasswordPresenter = new UpdatePasswordPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);
        ButterKnife.bind(this);

        setTitle(R.string.update_password);

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updatePasswordPresenter.updatePassword(etUpdatePassword.getText().toString(), UpdatePassword.this);
            }
        });
    }
}
