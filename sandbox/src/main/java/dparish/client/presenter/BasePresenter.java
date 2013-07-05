package dparish.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import dparish.client.view.View;

/**
 * @author dparish
 */
public class BasePresenter<V extends View> implements Presenter  {
    private V view;

    protected BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    /**
     * Access to the view, as the correct type.
     *
     * @return The view.
     */
    protected V view() {
        return view;
    }

}
