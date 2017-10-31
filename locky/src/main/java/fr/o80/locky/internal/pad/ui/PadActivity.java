package fr.o80.locky.internal.pad.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import fr.o80.locky.R;
import fr.o80.locky.internal.base.BaseActivity;
import fr.o80.locky.api.LockyConf;

public class PadActivity extends BaseActivity {

    public static final String EXTRA_ENROLLED = "a";

    @Inject
    protected LockyConf conf;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        LockyConf.component().inject(this);

        setTitle(conf.getTitleRes() != 0 ? conf.getTitleRes() : R.string.app_name);
    }

    public void confirmEnrolment() {
        setResult(LockyConf.RESULT_ENROLLED);
        finish();
    }

    public void confirmCheck() {
        setResult(LockyConf.RESULT_CHECK);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(LockyConf.RESULT_CANCELED);
        finish();
    }
}
