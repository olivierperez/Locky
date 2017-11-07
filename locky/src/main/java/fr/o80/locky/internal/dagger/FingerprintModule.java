package fr.o80.locky.internal.dagger;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;

import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import dagger.Module;
import dagger.Provides;

/**
 * @author Olivier Perez
 */
@Module
public final class FingerprintModule {

    private FingerprintModule() {
    }

    @Provides
    public static KeyguardManager provideKeyguardManager(Context context) {
        return (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Provides
    @Nullable
    public static FingerprintManager provideFingerprintManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        } else {
            return null;
        }
    }

    @Provides
    @Nullable
    public static KeyStore provideKeyStore() {
        try {
            return KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Provides
    @Nullable
    public static KeyGenerator provideKeyGenerator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Provides
    @Nullable
    public static Cipher provideCipher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                return Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
