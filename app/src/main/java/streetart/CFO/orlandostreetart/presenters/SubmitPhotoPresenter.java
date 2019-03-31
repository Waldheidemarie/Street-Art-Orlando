package streetart.CFO.orlandostreetart.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;

import streetart.CFO.orlandostreetart.views.SubmitPhoto;

/**
 * Created by Eric on 3/30/2019.
 */
public class SubmitPhotoPresenter {

    private static final String TAG = "SubmitPhotoPresenter";
    private Context context;

    //    Camera Permissions
    private int PERMISSION_GALLERY = 1;
    private int PERMISSION_CAMERA = 2;
    private String[] GALLERY_PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String[] CAMERA_PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };


    public SubmitPhotoPresenter(Context context) {
        this.context = context;
    }


    public void showUploadDialog() {

        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    ActivityCompat.requestPermissions((Activity) context,
                            CAMERA_PERMISSIONS,
                            PERMISSION_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                            ActivityCompat.requestPermissions((Activity) context,
                GALLERY_PERMISSIONS,
                PERMISSION_GALLERY);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

}
