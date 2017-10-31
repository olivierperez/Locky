package fr.o80.locky.internal.service;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.o80.locky.internal.pad.ui.PadActivity;

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

    public void unlock() {
        this.locked = false;
    }

    public void setPassword(String password) {
        pref.setPassword(password);
        unlock();
    }

    public boolean check(String password) {
        boolean checked = password != null && password.equals(pref.getPassword());
        if (checked) {
            unlock();
        }
        return checked;
    }
}
