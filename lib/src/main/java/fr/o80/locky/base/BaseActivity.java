package fr.o80.locky.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import fr.o80.locky.R;

/**
 * @author Olivier Perez
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        initialFragment(newInitialFragment());

        onCreated();
    }

    private void onCreated() {
    }

    private void initialFragment(Fragment fragment) {
        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }
    }

    protected abstract Fragment newInitialFragment();

}
