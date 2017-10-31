package fr.o80.locky.internal.pad.presenter;

import javax.inject.Inject;

import fr.o80.locky.internal.base.Presenter;
import fr.o80.locky.internal.component.Pad;
import fr.o80.locky.api.LockyConf;

/**
 * @author Olivier Perez
 */
public class ChooseMPinPresenter extends Presenter<ChooseMPinView> implements Pad.PadListener {

    private String first;

    private final LockyConf conf;

    @Inject
    public ChooseMPinPresenter(LockyConf conf) {
        this.conf = conf;
    }

    @Override
    public void onPassword(String password) {
        if (view == null) {
            return;
        }

        if (first == null) {
            first = password;
            view.clear();
            view.changeText(conf.getConfirmCodeRes());

        } else {
            if (first.equals(password)) {
                LockyConf.getInstance().setPassword(password);
                view.confirm();
            } else {
                first = null;
                view.clear();
                view.changeText(conf.getChooseCodeRes());
            }
        }
    }

    public void init() {
        view.changeText(conf.getChooseCodeRes());
    }
}
