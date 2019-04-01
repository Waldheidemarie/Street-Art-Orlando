package streetart.CFO.orlandostreetart.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.adapters.MyPhotosRecyclerViewAdapter;
import streetart.CFO.orlandostreetart.adapters.RecyclerViewAdapter;
import streetart.CFO.orlandostreetart.models.GetModel;
import streetart.CFO.orlandostreetart.presenters.MyPhotosPresenter;

public class MyPhotos extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pbProgress)
    ProgressBar progressBar;

    private static final String TAG = "MyPhotos";
    MyPhotosPresenter myPhotosPresenter = new MyPhotosPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        ButterKnife.bind(this);

        myPhotosPresenter.getMyPhotos();
    }


    public void setupAdapter(GetModel myPhotos) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyPhotosRecyclerViewAdapter(myPhotos));
    }
}
