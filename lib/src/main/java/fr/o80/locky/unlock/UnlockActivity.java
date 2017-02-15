package fr.o80.locky.unlock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.o80.locky.R;
import fr.o80.locky.R2;
import fr.o80.locky.service.LockyConf;

public class UnlockActivity extends AppCompatActivity {

    public static final int RESULT_UNLOCKED = 0;
    public static final int RESULT_LOCKED = 1;

    public static Intent newInstance(Context context) {
        return new Intent(context, UnlockActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pad);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.pad_ok)
    public void onOK() {
        LockyConf.getInstance().setLocked(false);
        setResult(RESULT_UNLOCKED);
        finish();
    }

    @Override
    public void onBackPressed() {
        LockyConf.getInstance().setLocked(true);
        setResult(RESULT_LOCKED);
        finish();
    }
}
