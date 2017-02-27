package fr.o80.locky.service;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

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

    private Context context;
    private int backgroundRes;
    private int colorRes;
    private int chooseCodeRes;
    private int confirmCodeRes;

    public static void init(LockyConf conf) {
        component = DaggerLockyComponent.builder()
                .lockyModule(new LockyModule(conf))
                .build();
    }

    public LockyConf(Context context) {
        this.context = context;
    }

    public static Locky getInstance() {
        return component.locky();
    }

    public static LockyComponent component() {
        return component;
    }

    public LockyConf withBackground(@DrawableRes @ColorRes int backgroundRes) {
        this.backgroundRes = backgroundRes;
        return this;
    }

    public LockyConf withTextColor(@ColorRes int colorRes) {
        this.colorRes = colorRes;
        return this;
    }

    public LockyConf withTexts(@StringRes int chooseCodeRes, @StringRes int confirmCodeRes) {
        this.chooseCodeRes = chooseCodeRes;
        this.confirmCodeRes = confirmCodeRes;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public int getColorRes() {
        return colorRes;
    }

    public int getChooseCodeRes() {
        return chooseCodeRes;
    }

    public int getConfirmCodeRes() {
        return confirmCodeRes;
    }
}
