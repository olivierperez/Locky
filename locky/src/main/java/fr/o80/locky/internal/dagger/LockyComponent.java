package fr.o80.locky.internal.dagger;

import javax.inject.Singleton;

import dagger.Component;
import fr.o80.locky.internal.component.Pad;
import fr.o80.locky.internal.pad.ui.CheckMPinFragment;
import fr.o80.locky.internal.pad.ui.ChooseMPinFragment;
import fr.o80.locky.internal.pad.ui.PadActivity;
import fr.o80.locky.internal.service.Locky;

/**
 * @author Olivier Perez
 */
@Component(modules = LockyModule.class)
@Singleton
public interface LockyComponent {
    Locky locky();

    void inject(PadActivity activity);
    void inject(ChooseMPinFragment fragment);
    void inject(CheckMPinFragment fragment);
    void inject(Pad pad);
}
