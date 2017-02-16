package fr.o80.locky.pad.presenter;

import android.support.annotation.StringRes;

import fr.o80.locky.base.PresenterView;

/**
 * @author Olivier Perez
 */
public interface ChooseMPinView extends PresenterView {
    void clear();
    void confirm();
    void changeText(@StringRes int strRes);
}
