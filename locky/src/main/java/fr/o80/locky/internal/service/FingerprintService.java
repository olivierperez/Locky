package fr.o80.locky.internal.service;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.inject.Inject;

import fr.o80.locky.api.LockyConf;

/**
 * @author Olivier Perez
 */
public class FingerprintService {

    private static final String KEY_NAME = "KEY_NAME";

    private final Context context;
    private final KeyguardManager keyguardManager;
    private final FingerprintManager fingerprintManager;
    private final KeyStore keyStore;
    private final KeyGenerator keyGenerator;
    private final Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    private CancellationSignal cancellationSignal;

    @Inject
    public FingerprintService(Context context, KeyguardManager keyguardManager, @Nullable FingerprintManager fingerprintManager, @Nullable KeyStore keyStore, @Nullable KeyGenerator keyGenerator, @Nullable Cipher cipher) {
        this.context = context;
        this.keyguardManager = keyguardManager;
        this.fingerprintManager = fingerprintManager;
        this.keyStore = keyStore;
        this.keyGenerator = keyGenerator;
        this.cipher = cipher;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean canEnableFingerprint() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED
                && keyguardManager.isKeyguardSecure()
                && fingerprintManager.hasEnrolledFingerprints();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public void generateKey() throws FingerprintException {
        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new FingerprintException(e);
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public boolean cipherInit() throws FingerprintException {
        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (Exception e) {
            throw new FingerprintException(e);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void startAuth(final FingerprintManager.AuthenticationCallback callback) {
        cancelAuth();
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                callback.onAuthenticationError(errorCode, errString);
                startAuth(callback);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
                callback.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                LockyConf.getInstance().unlock();
                callback.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                callback.onAuthenticationFailed();
            }
        }, null);
    }

    public void cancelAuth() {
        if (cancellationSignal != null && !cancellationSignal.isCanceled()) {
            cancellationSignal.cancel();
        }
    }

    public static class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }
}
