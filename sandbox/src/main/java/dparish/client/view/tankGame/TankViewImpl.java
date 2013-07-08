package dparish.client.view.tankGame;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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
public class TankViewImpl extends Composite implements TankView {
    interface TankViewImplUiBinder extends UiBinder<FlowPanel, TankViewImpl> {
    }

    private static TankViewImplUiBinder ourUiBinder = GWT.create(TankViewImplUiBinder.class);

    @UiField
    Label left;

    @UiField
    Label right;

    @UiField
    Label reverse;

    @UiField
    SimplePanel canvasContainer;

    private Canvas canvas;

    private TankWorker tankWorker;

    private Presenter presenter;

    @Inject
    public TankViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        canvas = Canvas.createIfSupported();
        canvasContainer.add(canvas);
        canvas.setStyleName(CommonResources.INSTANCE.css().canvas());
        canvas.setHeight("500px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(500);
        canvas.setCoordinateSpaceWidth(500);
    }

    @Override
    public void onLoad() {
        tankWorker.cancel();
    }

    @Override
    public void onUnload() {
        tankWorker.cancel();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        tankWorker = new TankWorker(presenter, canvas);
        this.presenter = presenter;
    }

    @UiHandler("start")
    public void onStartClick(ClickEvent e) {
        tankWorker.cancel();
        tankWorker.draw();
    }

    @UiHandler("stop")
    public void onStopClick(ClickEvent e) {
        tankWorker.cancel();
    }

    @UiHandler("left")
    public void onLeftClick(ClickEvent e) {
        presenter.turnLeft();
    }

    @UiHandler("right")
    public void onRightClick(ClickEvent e) {
        presenter.turnRight();
    }

    @UiHandler("reverse")
    public void onReverseClick(ClickEvent e) {
        presenter.reverse();
    }

}