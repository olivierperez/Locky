package fr.o80.locky.pad.presenter;

import javax.inject.Inject;

import fr.o80.locky.base.Presenter;
import fr.o80.locky.component.Pad;
import fr.o80.locky.service.LockyConf;

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
