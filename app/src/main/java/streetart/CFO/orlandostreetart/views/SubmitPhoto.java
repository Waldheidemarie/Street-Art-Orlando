package streetart.CFO.orlandostreetart.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    @BindView(R.id.btnSubmitPhoto)
    Button btnSubmitPhoto;
    @BindView(R.id.imagePreview)
    ImageView imagePreview;

    private static final String TAG = "SubmitPhoto";
    private SubmitPhotoPresenter submitPhotoPresenter;
    Uri art;

    //    Camera Permissions
    private int PERMISSION_GALLERY = 1;
    private int PERMISSION_CAMERA = 2;
    String mCameraFileName;
    private String[] GALLERY_PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String[] CAMERA_PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_photo);
        ButterKnife.bind(this);

        submitPhotoPresenter = new SubmitPhotoPresenter(this, this);

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
                        etLocationNotes.getText().toString());
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
                    ActivityCompat.requestPermissions(SubmitPhoto.this,
                            GALLERY_PERMISSIONS,
                            PERMISSION_GALLERY);
                } else if (items[item].equals("Take Photo")) {
                    ActivityCompat.requestPermissions(SubmitPhoto.this,
                            CAMERA_PERMISSIONS,
                            PERMISSION_CAMERA);
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
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(this, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                }
                return;
            }
//            CAMERA PERMISSIONS
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                    String outPath = getString(R.string.sd_card) + "photo.jpeg";
                    File outFile = new File(outPath);

                    mCameraFileName = outFile.toString();
                    Uri outUri = Uri.fromFile(outFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(this, R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
//            if (data != null) {
                art = data.getData();
                displayPreviewArt();
            }
            if (requestCode == 2) {
                art = Uri.fromFile(new File(mCameraFileName));
                Log.i(TAG, "onActivityResult: " + art.toString());
                displayPreviewArt();
            }
//        }
    }

    public void displayPreviewArt(){
     imgImageUpload.setVisibility(View.GONE);
     imagePreview.setVisibility(View.VISIBLE);
     Glide.with(this).load(art).into(imagePreview);
 }
}
