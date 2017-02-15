package fr.o80.locky.component;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.o80.locky.R;
import fr.o80.locky.R2;

/**
 * @author Olivier Perez
 */
public class Pad extends LinearLayout {

    public static final String KEY_OK = "KEY_OK";
    public static final String KEY_CLEAR = "CLEAR";

    private WeakReference<PadListener> listener;

    private StringBuilder password = new StringBuilder();

    public Pad(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_pad, this, true);
        ButterKnife.bind(this, view);
    }

    public void setListener(PadListener padListener) {
        listener = new WeakReference<>(padListener);
    }

    @OnClick({R2.id.pad_0, R2.id.pad_1, R2.id.pad_2, R2.id.pad_3, R2.id.pad_4, R2.id.pad_5, R2.id.pad_6, R2.id.pad_7, R2.id.pad_8, R2.id.pad_9})
    protected void onPadN(View v) {
        String key = ((Button) v).getText().toString();
        password.append(key);
    }

    @OnClick(R2.id.pad_ok)
    protected void onOK() {
        PadListener padListener = listener.get();
        if (padListener != null) {
            padListener.onPassword(password.toString());
        }
    }

    @OnClick(R2.id.pad_clear)
    protected void onClear() {
        if (password.length() > 0) {
            password.deleteCharAt(password.length() - 1);
        }
    }

    public interface PadListener {
        void onPassword(String password);
    }
}
