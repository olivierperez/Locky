package fr.o80.locky.enrolment.ui;

import javax.inject.Inject;

import fr.o80.locky.R;
import fr.o80.locky.base.BaseFragment;
import fr.o80.locky.enrolment.presenter.ChooseMPinPresenter;
import fr.o80.locky.enrolment.presenter.ChooseMPinView;
import fr.o80.locky.service.LockyConf;

/**
 * @author Olivier Perez
 */
public class ChooseMPinFragment extends BaseFragment implements ChooseMPinView {

    @Inject
    protected ChooseMPinPresenter presenter;

    public static ChooseMPinFragment newInstance() {
        return new ChooseMPinFragment();
    }

    @Override
    protected void inject() {
        LockyConf.component().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_enrolment;
    }

    @Override
    protected ChooseMPinPresenter presenter() {
        return presenter;
    }
}
