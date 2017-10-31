package fr.o80.locky.internal.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.o80.locky.api.LockyConf;

/**
 * @author Olivier Perez
 */
@Module
public class LockyModule {

    private LockyConf conf;

    public LockyModule(LockyConf conf) {
        this.conf = conf;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return conf.getContext();
    }

    @Provides
    @Singleton
    public LockyConf provideLockyConf() {
        return conf;
    }
}
