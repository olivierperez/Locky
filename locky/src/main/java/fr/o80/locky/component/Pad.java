package fr.o80.locky.component;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import fr.o80.locky.R;
import fr.o80.locky.R2;
import fr.o80.locky.service.LockyConf;

/**
 * @author Olivier Perez
 */
public class Pad extends LinearLayout {

    @Inject
    protected LockyConf conf;

    @BindView(R2.id.pad_title)
    protected TextView titleTextView;

    @BindView(R2.id.pad_password)
    protected TextView passwordTextView;

    private WeakReference<PadListener> listener;

    private StringBuilder password = new StringBuilder();

    public Pad(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LockyConf.component().inject(this);

        setOrientation(VERTICAL);

        View view = inflate(getContext(), R.layout.pad, this);
        ButterKnife.bind(this, view);

        if (conf.getColorRes() != 0) {
            setTextColor(ContextCompat.getColorStateList(conf.getContext(), conf.getColorRes()));
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            titleTextView.setTextColor(colorStateList);
            passwordTextView.setTextColor(colorStateList);
            setTextColor(R.id.pad_0, colorStateList);
            setTextColor(R.id.pad_1, colorStateList);
            setTextColor(R.id.pad_2, colorStateList);
            setTextColor(R.id.pad_3, colorStateList);
            setTextColor(R.id.pad_4, colorStateList);
            setTextColor(R.id.pad_5, colorStateList);
            setTextColor(R.id.pad_6, colorStateList);
            setTextColor(R.id.pad_7, colorStateList);
            setTextColor(R.id.pad_8, colorStateList);
            setTextColor(R.id.pad_9, colorStateList);
            setTint(R.id.pad_ok, R.drawable.ic_done, colorStateList);
            setTint(R.id.pad_clear, R.drawable.ic_backspace, colorStateList);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setTint(@IdRes int id, @DrawableRes int drawableRes, ColorStateList colorStateList) {
        ((ImageButton) findViewById(id)).setColorFilter(colorStateList.getDefaultColor());
    }

    private void setTextColor(@IdRes int id, ColorStateList colorStateList) {
        ((Button) findViewById(id)).setTextColor(colorStateList);
    }

    public void setListener(PadListener padListener) {
        listener = new WeakReference<>(padListener);
    }

    @OnClick({R2.id.pad_0, R2.id.pad_1, R2.id.pad_2, R2.id.pad_3, R2.id.pad_4, R2.id.pad_5, R2.id.pad_6, R2.id.pad_7, R2.id.pad_8, R2.id.pad_9})
    protected void onPadN(View v) {
        String key = ((Button) v).getText().toString();
        password.append(key);
        updateTextField();
    }

    private void updateTextField() {
        StringBuilder sb = new StringBuilder(password.length());
        for (int i = 0; i < password.length(); i++) {
            sb.append("*");
        }
        passwordTextView.setText(sb.toString());
    }

    @OnClick(R2.id.pad_clear)
    protected void onClear() {
        if (password.length() > 0) {
            password.deleteCharAt(password.length() - 1);
        }
        updateTextField();
    }


    @OnLongClick(R2.id.pad_clear)
    protected boolean onLongClear() {
        clear();
        return true;
    }

    @OnClick(R2.id.pad_ok)
    protected void onOK() {
        PadListener padListener = listener.get();
        if (padListener != null) {
            padListener.onPassword(password.toString());
        }
    }

    public void clear() {
        password.delete(0, password.length());
        updateTextField();
    }

    public void wrongPassword() {
        passwordTextView.animate()
                .setInterpolator(new CycleInterpolator(3))
                .translationXBy(10)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        clear();
                    }
                });
    }

    public void setTitle(@StringRes int titleRes) {
        titleTextView.setText(titleRes);
        titleTextView.setVisibility(View.VISIBLE);
    }

    public interface PadListener {
        void onPassword(String password);
    }
}
