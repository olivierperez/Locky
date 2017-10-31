package fr.o80.locky.internal.pad.presenter;

import javax.inject.Inject;

import fr.o80.locky.internal.base.Presenter;
import fr.o80.locky.internal.component.Pad;
import fr.o80.locky.api.LockyConf;

/**
 * @author Olivier Perez
 */
public class CheckMPinPresenter extends Presenter<CheckMPinView> implements Pad.PadListener {

    @Inject
    public CheckMPinPresenter() {
    }

    @Override
    public void onPassword(String password) {
        if (LockyConf.getInstance().check(password)) {
            view.confirm();
        } else {
            view.wrongPassword();
        }
    }
}
