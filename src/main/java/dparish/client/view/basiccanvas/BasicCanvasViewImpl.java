package dparish.client.view.basiccanvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;

/**
 * @author dparish
 */
public class BasicCanvasViewImpl extends Composite implements BasicCanvasView {
    interface BasicCanvasViewImplUiBinder extends UiBinder<FlowPanel, BasicCanvasViewImpl> {
    }

    private static BasicCanvasViewImplUiBinder ourUiBinder = GWT.create(BasicCanvasViewImplUiBinder.class);

    @UiField
    SimplePanel canvasContainer;

    @UiField
    Label rotate;

    @UiField
    Label text;

    @UiField
    Label rotateCenter;

    @UiField
    Label dragAndDrop;

    private Presenter presenter;

    private Canvas canvas;

    private BasicCanvasWorker worker;

    @Inject
    public BasicCanvasViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        canvas = Canvas.createIfSupported();
        canvasContainer.add(canvas);
        canvas.setStyleName(CommonResources.INSTANCE.css().canvas());
        canvas.setHeight("500px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(500);
        canvas.setCoordinateSpaceWidth(500);
        worker = new BasicCanvasWorker(canvas);

        canvas.addMouseMoveHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent mouseMoveEvent) {
                worker.mouseMoved(mouseMoveEvent);
            }
        });
        canvas.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent mouseDownEvent) {
                worker.mouseDown(mouseDownEvent);
            }
        });
        canvas.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent mouseUpEvent) {
                worker.mouseUp(mouseUpEvent);
            }
        });
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
    }

    @UiHandler("rotate")
    public void onRotateClicked(ClickEvent e) {
        clearAll();
        worker.simpleRotate();
    }

    @UiHandler("text")
    public void onTextButtonClicked(ClickEvent e) {
        clearAll();
        worker.text();
    }

    @UiHandler("rotateCenter")
    public void onRotateCenterClicked(ClickEvent e) {
        clearAll();
        worker.rotateCenter();
    }

    @UiHandler("dragAndDrop")
    public void onDragAndDropClicked(ClickEvent e) {
        worker.drawRectangle();
    }

    private void clearAll() {
        worker.clear();
    }


}