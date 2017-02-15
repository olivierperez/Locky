package fr.o80.locky.pad.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import fr.o80.locky.base.BaseActivity;
import fr.o80.locky.service.LockyConf;

public class PadActivity extends BaseActivity {

    public static final String EXTRA_ENROLLED = "a";

    public static Intent newInstance(Context context, boolean enrolled) {
        Intent intent = new Intent(context, PadActivity.class);
        intent.putExtra(EXTRA_ENROLLED, enrolled);
        return intent;
    }

    @Override
    protected Fragment newInitialFragment() {
        Bundle extras = getIntent().getExtras();
        if (extras.getBoolean(EXTRA_ENROLLED)) {
            return CheckMPinFragment.newInstance();
        } else {
            return ChooseMPinFragment.newInstance();
        }
    }

    public void confirmEnrolment() {
        setResult(LockyConf.RESULT_ENROLLED);
        finish();
    }

    public void confirmCheck() {
        setResult(LockyConf.RESULT_CHECK);
        finish();
    }
}
