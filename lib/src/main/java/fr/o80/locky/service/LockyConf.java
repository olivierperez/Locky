package fr.o80.locky.service;

import android.content.Context;

import fr.o80.locky.dagger.DaggerLockyComponent;
import fr.o80.locky.dagger.LockyComponent;
import fr.o80.locky.dagger.LockyModule;

/**
 * @author Olivier Perez
 */
public final class LockyConf {

    public static final int RESULT_CANCELED = -1;
    public static final int RESULT_ENROLLED = 1;
    public static final int RESULT_CHECK = 2;

    private static LockyComponent component;

    public static void init(Context context) {
        component = DaggerLockyComponent.builder()
                .lockyModule(new LockyModule(context))
                .build();
    }

    private LockyConf() {
    }

    public static Locky getInstance() {
        return component.locky();
    }

    public static LockyComponent component() {
        return component;
    }
}
