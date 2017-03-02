package fr.o80.locky.pad.presenter;

import fr.o80.locky.base.PresenterView;

/**
 * @author Olivier Perez
 */
public interface CheckMPinView extends PresenterView {
    void confirm();
    void wrongPassword();
}
