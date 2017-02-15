package fr.o80.locky.pad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import fr.o80.locky.R;
import fr.o80.locky.R2;
import fr.o80.locky.base.BaseFragment;
import fr.o80.locky.component.Pad;
import fr.o80.locky.pad.presenter.ChooseMPinPresenter;
import fr.o80.locky.pad.presenter.ChooseMPinView;
import fr.o80.locky.service.LockyConf;

/**
 * @author Olivier Perez
 */
public class ChooseMPinFragment extends BaseFragment implements ChooseMPinView {

    @Inject
    protected ChooseMPinPresenter presenter;

    @BindView(R2.id.pad)
    protected Pad pad;

    public static ChooseMPinFragment newInstance() {
        return new ChooseMPinFragment();
    }

    @Override
    protected void inject() {
        LockyConf.component().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_pad;
    }

    @Override
    protected ChooseMPinPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pad.setListener(presenter);
    }

    @Override
    public void clear() {
        pad.clear();
    }

    @Override
    public void confirm() {
        Log.d("ChooseMPinFragment", "Confirmed");
    }
}