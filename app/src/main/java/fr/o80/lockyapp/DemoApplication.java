package fr.o80.lockyapp;

import android.app.Application;

import fr.o80.locky.service.LockyConf;

/**
 * @author Olivier Perez
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LockyConf.init(this);
    }
}
