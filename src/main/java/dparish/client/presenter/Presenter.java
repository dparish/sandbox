package dparish.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author dparish
 */
public interface Presenter {
    /**
     * Tell the presenter to display its view in the provided container.
     *
     * @param container The container for the view that this presenter is controlling.
     */
    void go(final HasWidgets container);
}