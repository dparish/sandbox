package dparish.client.view.basiccanvas;

import com.google.inject.ImplementedBy;
import dparish.client.view.View;

/**
 * @author dparish
 */
@ImplementedBy(BasicCanvasViewImpl.class)
public interface BasicCanvasView extends View {

    void setPresenter(Presenter p);

    interface Presenter {

    }
}
