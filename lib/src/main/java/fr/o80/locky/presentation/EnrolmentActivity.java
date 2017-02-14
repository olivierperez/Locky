package fr.o80.locky.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.o80.locky.R;

public class EnrolmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolment);
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, EnrolmentActivity.class);
    }
}
