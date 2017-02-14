package fr.o80.lockyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
