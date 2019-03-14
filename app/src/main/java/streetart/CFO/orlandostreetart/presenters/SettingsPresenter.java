package streetart.CFO.orlandostreetart.presenters;

import streetart.CFO.orlandostreetart.views.SettingsFragment;

/**
 * Created by Eric on 3/13/2019.
 */
public class SettingsPresenter {

    private static final String TAG = "SettingsPresenter";
    SettingsFragment settingsView;

    public SettingsPresenter(SettingsFragment settingsFragment) {
        this.settingsView = settingsFragment;
    }

}
