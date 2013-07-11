package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.windowbox.WindowBoxView;

/**
 * @author dparish
 */
@Singleton
public class WindowBoxPresenter extends BasePresenter<WindowBoxView> implements WindowBoxView.Presenter {

    @Inject
    public WindowBoxPresenter(WindowBoxView view) {
        super(view);
        view.setPresenter(this);
    }
}
