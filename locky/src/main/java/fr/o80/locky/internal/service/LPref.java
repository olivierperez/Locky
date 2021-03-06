package fr.o80.locky.internal.service;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Olivier Perez
 */
@Singleton
public class LPref {

    private static final String SHAREDPREF_NAME = "fr.o80.locky.internal.service.LPref";

    private static final String KEY_PASSWORD = "A";

    private final SharedPreferences prefs;

    @Inject
    public LPref(Context context) {
        prefs = context.getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isEnrolled() {
        return prefs.contains(KEY_PASSWORD);
    }

    public void setPassword(String password) {
        prefs.edit()
                .putString(KEY_PASSWORD, password)
                .apply();
    }

    public String getPassword() {
        return prefs.getString(KEY_PASSWORD, null);
    }
}
