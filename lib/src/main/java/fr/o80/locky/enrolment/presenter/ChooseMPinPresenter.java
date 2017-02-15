package fr.o80.locky.enrolment.presenter;

import android.util.Log;

import javax.inject.Inject;

import fr.o80.locky.base.Presenter;
import fr.o80.locky.component.Pad;
import fr.o80.locky.service.LockyConf;

/**
 * @author Olivier Perez
 */
public class ChooseMPinPresenter extends Presenter implements Pad.PadListener {

    private String first;

    @Inject
    public ChooseMPinPresenter() {
    }

    @Override
    public void onPassword(String password) {
        if (view == null) {
            return;
        }

        if (first == null) {
            first = password;
            view.clear();
        } else {
            if (first.equals(password)) {
                LockyConf.getInstance().setPassword(password);
                view.confirm();
            }
        }
        Log.d("Password", password);
    }
}
