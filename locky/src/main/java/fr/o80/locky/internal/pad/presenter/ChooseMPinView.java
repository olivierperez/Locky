package fr.o80.locky.internal.pad.presenter;

import android.support.annotation.StringRes;

import fr.o80.locky.internal.base.PresenterView;

/**
 * @author Olivier Perez
 */
public interface ChooseMPinView extends PresenterView {
    void clear();
    void confirm();
    void changeText(@StringRes int strRes);
}
