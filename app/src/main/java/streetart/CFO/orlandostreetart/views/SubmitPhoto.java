package streetart.CFO.orlandostreetart.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.SubmitPhotoPresenter;

public class SubmitPhoto extends AppCompatActivity {

    @BindView(R.id.imageUpload)
    ImageView imgImageUpload;
    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.etArtist)
    EditText etArtist;
    @BindView(R.id.etLocationNotes)
    EditText etLocationNotes;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.btnSubmitPhoto)
    Button btnSubmitPhoto;
    @BindView(R.id.imagePreview)
    ImageView imagePreview;

    private static final String TAG = "SubmitPhoto";
    private SubmitPhotoPresenter submitPhotoPresenter;
    Uri art;
    double longitude;
    double latitude;

    String mCameraFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_photo);
        ButterKnife.bind(this);

        submitPhotoPresenter = new SubmitPhotoPresenter(this, this);

        submitPhotoPresenter.locationPermissions();

        imgImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUploadDialog();
            }
        });

        btnSubmitPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPhotoPresenter.getArtInfo(etTitle.getText().toString(), etArtist.getText().toString(),
                        etLocationNotes.getText().toString(), etDescription.getText().toString(),
                        latitude, longitude, art);
            }
        });
    }


    public void showUploadDialog() {

        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Library")) {
                    submitPhotoPresenter.galleryPermissions();
                } else if (items[item].equals("Take Photo")) {
                    submitPhotoPresenter.cameraPermissions();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
//            GALLERY PERMISSIONS
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    submitPhotoPresenter.galleryImage();
                } else {
                    Toast.makeText(this, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                }
                return;
            }
//            CAMERA PERMISSIONS
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    submitPhotoPresenter.cameraImage();
                } else {
                    Toast.makeText(this, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                }
            }
//            Location Permission
            case 3: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    submitPhotoPresenter.getLocation();
                } else {
                    Log.i(TAG, "onRequestPermissionsResult: denied");
                    Toast.makeText(this, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            art = data.getData();
            displayPreviewArt(art);
        }
        if (requestCode == 2) {
            Log.i(TAG, "onActivityResult: " + data.getData());
//            art = data.getData();
//            art = Uri.fromFile(new File(mCameraFileName));
            displayPreviewArt(art);
        }
    }

    public void displayPreviewArt(Uri art) {
        imgImageUpload.setVisibility(View.GONE);
        imagePreview.setVisibility(View.VISIBLE);
        Glide.with(this).load(art).into(imagePreview);
    }
}
