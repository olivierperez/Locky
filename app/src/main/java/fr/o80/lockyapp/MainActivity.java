package fr.o80.lockyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import fr.o80.locky.service.LockyConf;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (LockyConf.getInstance().isLocked()) {
            Intent intent = LockyConf.getInstance().lockActivity(this);
            startActivityForResult(intent, REQUEST_CODE_LOCK);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_LOCK) {
            switch (resultCode) {
                case LockyConf.RESULT_CANCELED:
                    finish();
                    break;
                case LockyConf.RESULT_CHECK:
                    Toast.makeText(this, "Authentication checked", Toast.LENGTH_SHORT).show();
                    break;
                case LockyConf.RESULT_ENROLLED:
                    Toast.makeText(this, "Enrolment finished", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
