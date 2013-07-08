package dparish.client.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.basiccanvas.BasicCanvasView;

/**
 * @author dparish
 */
@Singleton
public class BasicCanvasPresenter extends BasePresenter<BasicCanvasView> implements BasicCanvasView.Presenter {

    @Inject
    public BasicCanvasPresenter(BasicCanvasView view) {
        super(view);
        view.setPresenter(this);
    }
}
