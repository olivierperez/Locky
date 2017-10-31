package fr.o80.locky.internal.pad.presenter;

import fr.o80.locky.internal.base.PresenterView;

/**
 * @author Olivier Perez
 */
public interface CheckMPinView extends PresenterView {
    void confirm();
    void wrongPassword();
}
