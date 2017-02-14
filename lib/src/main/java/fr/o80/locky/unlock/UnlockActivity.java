package fr.o80.locky.unlock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import fr.o80.locky.R;
import fr.o80.locky.service.LockyConf;

public class UnlockActivity extends AppCompatActivity {

    public static final int RESULT_UNLOCKED = 0;
    public static final int RESULT_LOCKED = 1;

    private EditText password;

    public static Intent newInstance(Context context) {
        return new Intent(context, UnlockActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pad);

        password = (EditText) findViewById(R.id.pad_password);

        findViewById(R.id.pad_validate)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("0000".equals(password.getText().toString())) {
                            confirmed();
                        }
                    }
                });
    }

    private void confirmed() {
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
