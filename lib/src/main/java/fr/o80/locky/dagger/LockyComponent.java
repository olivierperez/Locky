package fr.o80.locky.dagger;

import javax.inject.Singleton;

import dagger.Component;
import fr.o80.locky.service.Locky;

/**
 * @author Olivier Perez
 */
@Component(modules = LockyModule.class)
@Singleton
public interface LockyComponent {
    Locky locky();
}
