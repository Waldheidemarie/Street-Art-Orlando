package streetart.CFO.orlandostreetart.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;

public class FullImageView extends AppCompatActivity {

    @BindView(R.id.imgFullImageView)
    ImageView imgFullImageView;
    @BindView(R.id.imgCloseFullImg)
    ImageView imgCloseFullImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_view);
        ButterKnife.bind(this);

//        Load image
        Glide.with(this)
                .load(getIntent().getStringExtra("photo"))
                .into(imgFullImageView);

        imgCloseFullImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
