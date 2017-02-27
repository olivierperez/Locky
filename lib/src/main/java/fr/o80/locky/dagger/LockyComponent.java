package fr.o80.locky.dagger;

import javax.inject.Singleton;

import dagger.Component;
import fr.o80.locky.component.Pad;
import fr.o80.locky.pad.ui.CheckMPinFragment;
import fr.o80.locky.pad.ui.ChooseMPinFragment;
import fr.o80.locky.service.Locky;

/**
 * @author Olivier Perez
 */
@Component(modules = LockyModule.class)
@Singleton
public interface LockyComponent {
    Locky locky();

    void inject(ChooseMPinFragment fragment);
    void inject(CheckMPinFragment fragment);
    void inject(Pad pad);
}
