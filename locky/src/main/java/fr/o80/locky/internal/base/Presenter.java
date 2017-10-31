package fr.o80.locky.internal.base;

/**
 * @author Olivier Perez
 */
public abstract class Presenter<T extends PresenterView> {

    protected T view;

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }
}
