package fr.o80.locky.enrolment.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import fr.o80.locky.R;
import fr.o80.locky.base.BaseActivity;

public class EnrolmentActivity extends BaseActivity {

    public static Intent newInstance(Context context) {
        return new Intent(context, EnrolmentActivity.class);
    }

    @Override
    protected Fragment newInitialFragment() {
        return ChooseMPinFragment.newInstance();
    }
}
