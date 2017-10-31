package fr.o80.lockyapp;

import android.app.Application;

import fr.o80.locky.api.LockyConf;

/**
 * @author Olivier Perez
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LockyConf.init(new LockyConf(this)
                .withBackground(R.color.colorPrimary)
                .withTextColor(android.R.color.white)
                .withTitle(R.string.app_name)
                .withTexts(R.string.choose_code, R.string.confirm_code));
    }
}
