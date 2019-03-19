package streetart.CFO.orlandostreetart.presenters;

import streetart.CFO.orlandostreetart.views.Details;

/**
 * Created by Eric on 3/15/2019.
 */
public class DetailsPresenter  {

    private static final String TAG = "DetailsPresenter";
    private Details view;

    public DetailsPresenter(Details details) {
        this.view = details;
    }

    public void getPassedDetails() {
       String photoUrl = view.getIntent().getStringExtra("photoUrl");

//       Display details in view
        view.displayDetails(photoUrl);
    }
}
