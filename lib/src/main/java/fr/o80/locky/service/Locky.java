package fr.o80.locky.service;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.o80.locky.pad.ui.PadActivity;

/**
 * @author Olivier Perez
 */
@Singleton
public class Locky {

    private boolean locked = true;

    private final LPref pref;

    @Inject
    public Locky(LPref pref) {
        this.pref = pref;
    }

    public boolean isLocked() {
        return locked;
    }

    public Intent lockActivity(Context context) {
        return PadActivity.newInstance(context, pref.isEnrolled());
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setPassword(String password) {
        pref.setPassword(password);
        setLocked(false);
    }

    public boolean check(String password) {
        return password != null && password.equals(pref.getPassword());
    }
}
