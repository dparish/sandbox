package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class BasicCanvasViewImpl extends Composite implements BasicCanvasView {
    interface BasicCanvasViewImplUiBinder extends UiBinder<FlowPanel, BasicCanvasViewImpl> {
    }

    private static BasicCanvasViewImplUiBinder ourUiBinder = GWT.create(BasicCanvasViewImplUiBinder.class);

    interface Style extends CssResource {
        String canvas();
    }

    @UiField
    SimplePanel canvasContainer;

    @UiField
    Style style;

    @UiField
    Label rotate;

    @UiField
    Label text;

    private Presenter presenter;

    private Canvas canvas;

    private BasicCanvasWorker worker;

    @Inject
    public BasicCanvasViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        canvas = Canvas.createIfSupported();
        canvasContainer.add(canvas);
        canvas.setStyleName(style.canvas());
        canvas.setHeight("500px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(500);
        canvas.setCoordinateSpaceWidth(500);
        worker = new BasicCanvasWorker(canvas);
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
    }

    @UiHandler("rotate")
    public void onRotateClicked(ClickEvent e) {
        worker.clear();
        worker.simpleRotate();
    }

    @UiHandler("text")
    public void onTextButtonClicked(ClickEvent e) {
        worker.clear();
        worker.text();
    }
}