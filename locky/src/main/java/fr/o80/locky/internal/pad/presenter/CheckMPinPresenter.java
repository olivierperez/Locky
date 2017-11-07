package fr.o80.locky.internal.pad.presenter;

import android.hardware.fingerprint.FingerprintManager;

import javax.inject.Inject;

import fr.o80.locky.api.LockyConf;
import fr.o80.locky.internal.base.Presenter;
import fr.o80.locky.internal.component.Pad;
import fr.o80.locky.internal.service.FingerprintService;
import timber.log.Timber;

/**
 * @author Olivier Perez
 */
public class CheckMPinPresenter extends Presenter<CheckMPinView> implements Pad.PadListener {

    private final FingerprintService fingerprintService;

    @Inject
    public CheckMPinPresenter(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }

    @Override
    public void onPassword(String password) {
        if (LockyConf.getInstance().check(password)) {
            view.confirm();
        } else {
            view.wrongPassword();
        }
    }

    public void init() {
        if (fingerprintService.canEnableFingerprint()) {
            Timber.d("Can enable fingerprint");

            try {
                fingerprintService.generateKey();
                fingerprintService.cipherInit();
                fingerprintService.startAuth(new FingerprintManager.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        view.confirm();
                    }
                });
            } catch (FingerprintService.FingerprintException e) {
                e.printStackTrace();
            }
        } else {
            Timber.d("Cannot use fingerprint");
        }
    }
}
