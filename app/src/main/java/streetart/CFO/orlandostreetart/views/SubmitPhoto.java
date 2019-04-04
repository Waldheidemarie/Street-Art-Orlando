package streetart.CFO.orlandostreetart.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.SubmitPhotoPresenter;

public class SubmitPhoto extends AppCompatActivity {

//    TODO: save info on rotation

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_photo);
        ButterKnife.bind(this);

        setTitle(R.string.submit_photo);

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
//                Convert image into base 64
                convertBase64();

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            art = data.getData();
            displayPreviewArt(art);
            getLongLat(art);
        }
        if (requestCode == 2) {
//            displayPreviewArt(submitPhotoPresenter.getImageUri(this, (Bitmap) data.getExtras().get("data")));
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgImageUpload.setVisibility(View.GONE);
            imagePreview.setVisibility(View.VISIBLE);
            Glide.with(this).load(photo).into(imagePreview);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getLongLat(Uri artUri){
        InputStream in = null;
        try {
            in = getContentResolver().openInputStream(artUri);
            ExifInterface exifInterface = new ExifInterface(in);
            Log.i(TAG, "getLongLat: " + convertRationalLatLonToFloat(exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE),
                    exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)));
            Log.i(TAG, "getLongLat: " + convertRationalLatLonToFloat(exifInterface.getAttribute
                            (ExifInterface.TAG_GPS_LONGITUDE),
                    exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)));
            // Now you can extract any Exif tag you want
            // Assuming the image is a JPEG or supported raw format
        } catch (IOException e) {
            // Handle any errors
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {}
            }
        }
        }

    private static float convertRationalLatLonToFloat(
            String rationalString, String ref) {
        try {
            String [] parts = rationalString.split(",");
            String [] pair;
            pair = parts[0].split("/");
            double degrees = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            pair = parts[1].split("/");
            double minutes = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            pair = parts[2].split("/");
            double seconds = Double.parseDouble(pair[0].trim())
                    / Double.parseDouble(pair[1].trim());
            double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
            if ((ref.equals("S") || ref.equals("W"))) {
                return (float) -result;
            }
            return (float) result;
        } catch (NumberFormatException e) {
            // Some of the nubmers are not valid
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            // Some of the rational does not follow the correct format
            throw new IllegalArgumentException();
        }
    }

    private void convertBase64(){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), art);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();

            String encodedString = "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
            art = Uri.parse(encodedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayPreviewArt(Uri art) {
        imgImageUpload.setVisibility(View.GONE);
        imagePreview.setVisibility(View.VISIBLE);
        Glide.with(this).load(art).into(imagePreview);
    }
}
