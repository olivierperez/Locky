package fr.o80.locky.enrolment.presenter;

import android.util.Log;

import javax.inject.Inject;

import fr.o80.locky.base.Presenter;
import fr.o80.locky.component.Pad;

/**
 * @author Olivier Perez
 */
public class ChooseMPinPresenter extends Presenter implements Pad.PadListener {

    @Inject
    public ChooseMPinPresenter() {
    }

    @Override
    public void onPassword(String password) {
        Log.d("Password", password);
    }
}
