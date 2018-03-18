package fr.o80.locky.internal.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author Olivier Perez
 */
public class TintableImageView extends AppCompatImageView {

    private ColorStateList tint;

    public TintableImageView(Context context) {
        super(context);
    }

    public TintableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TintableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (tint != null && tint.isStateful()) {
            updateTintColor();
        }
    }

    public void setTint(ColorStateList tint) {
        this.tint = tint;
        updateTintColor();
    }

    private void updateTintColor() {
        int color = tint.getColorForState(getDrawableState(), 0);
        setColorFilter(color);
    }
}
