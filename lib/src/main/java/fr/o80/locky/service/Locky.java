package fr.o80.locky.service;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.o80.locky.enrolment.ui.EnrolmentActivity;
import fr.o80.locky.unlock.UnlockActivity;

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
        if (pref.isEnrolled()) {
            return UnlockActivity.newInstance(context);
        } else {
            return EnrolmentActivity.newInstance(context);
        }
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
