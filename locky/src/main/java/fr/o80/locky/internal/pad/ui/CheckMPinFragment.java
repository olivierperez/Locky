package fr.o80.locky.internal.pad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import fr.o80.locky.R;
import fr.o80.locky.R2;
import fr.o80.locky.api.LockyConf;
import fr.o80.locky.internal.base.BaseFragment;
import fr.o80.locky.internal.base.Presenter;
import fr.o80.locky.internal.component.Pad;
import fr.o80.locky.internal.pad.presenter.CheckMPinPresenter;
import fr.o80.locky.internal.pad.presenter.CheckMPinView;

/**
 * @author Olivier Perez
 */
public class CheckMPinFragment extends BaseFragment implements CheckMPinView {

    @Inject
    protected CheckMPinPresenter presenter;

    @Inject
    protected LockyConf conf;

    @BindView(R2.id.pad)
    protected Pad pad;

    public static CheckMPinFragment newInstance() {
        return new CheckMPinFragment();
    }

    @Override
    protected void inject() {
        LockyConf.component().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment;
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pad.setListener(presenter);
        view.setBackgroundResource(conf.getBackgroundRes());

        presenter.init();
    }

    @Override
    public void showFingerprint() {
        pad.showFingerprint();
    }

    @Override
    public void confirm() {
        ((PadActivity) getActivity()).confirmCheck();
    }

    @Override
    public void wrongPassword() {
        pad.wrongPassword();
    }
}
