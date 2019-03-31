package streetart.CFO.orlandostreetart.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.SubmitPhotoPresenter;

public class SubmitPhoto extends AppCompatActivity {

    @BindView(R.id.imageUpload)
    ImageView imgImageUpload;
    @BindView(R.id.etTitle)
    TextView etTitle;
    @BindView(R.id.etArtist)
    TextView etArtist;
    @BindView(R.id.etLocationNotes)
    TextView etLocationNotes;
    @BindView(R.id.btnSubmitPhoto)
    Button btnSubmitPhoto;

    private static final String TAG = "SubmitPhoto";
    private SubmitPhotoPresenter submitPhotoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_photo);
        ButterKnife.bind(this);

        submitPhotoPresenter = new SubmitPhotoPresenter(this);

        imgImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPhotoPresenter.showUploadDialog();
            }
        });
    }


    //    THIS ASKS FOR PERMISSION FOR GALLERY AND CAMERA AND CREATES INTENT
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
//            GALLERY PERMISSIONS
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // GALLERY PERMISSION GRANTED. THIS OPENS GALLERY CHOOSER
                    Log.i(TAG, "onRequestPermissionsResult: Granted");
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, 2);
                } else {
                    // permission denied, boo!
                    Log.i(TAG, "onRequestPermissionsResult: Denied");
//                    Toast.makeText(this, R.string.permisdenied, Toast.LENGTH_SHORT).show();
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

//                    Date date = new Date();
//                    @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("-mm-ss");
//
//                    String newPicFile = df.format(date) + getString(R.string.jpg);
//                    String outPath = getString(R.string.sdcard) + newPicFile;
//                    File outFile = new File(outPath);
//
//                    mCameraFileName = outFile.toString();
//                    Uri outuri = Uri.fromFile(outFile);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
//                    startActivityForResult(intent, 2);
                } else {
//                    Toast.makeText(this, R.string.premisden, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
