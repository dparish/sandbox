package dparish.client.view.imagecrop;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;
import dparish.client.resources.MainMessages;
import dparish.client.widgets.SquareWindowPane;

/**
 * @author dparish
 */
public class ImageCropImpl extends Composite implements ImageCropView {
    interface ImageCropImplUiBinder extends UiBinder<FlowPanel, ImageCropImpl> {
    }

    private static ImageCropImplUiBinder ourUiBinder = GWT.create(ImageCropImplUiBinder.class);

    @UiField
    SimplePanel canvasContainer;

    @UiField
    SimplePanel hiddenCanvasContainer;

    @UiField
    Image image;

    @UiField
    SquareWindowPane windowPane;

    @UiField
    Label save;

    @UiField
    Label saveMessage;

    private Presenter presenter;

    private Canvas canvas;

    private Canvas hiddenCanvas;

    private ImageCropWorker worker;

    @Inject
    public ImageCropImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        canvas = Canvas.createIfSupported();
        canvasContainer.add(canvas);
        sizeCanvas(canvas);
        hiddenCanvas = Canvas.createIfSupported();
        hiddenCanvasContainer.add(hiddenCanvas);
        sizeCanvas(hiddenCanvas);

        windowPane.addValueChangeHandler(new ValueChangeHandler<SquareWindowPane.PositionInfo>() {
            @Override
            public void onValueChange(ValueChangeEvent<SquareWindowPane.PositionInfo> event) {
                worker.windowMoved(event.getValue());
            }
        });
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
        worker = new ImageCropWorker(canvas, hiddenCanvas);
        worker.render(image);
        // worker.clip();
    }

    @Override
    public void displaySaveMessage(String message, boolean isError) {
        saveMessage.removeStyleName(CommonResources.INSTANCE.css().errorMessage());

        if (isError) {
            saveMessage.setText(MainMessages.INSTANCE.errorSavingFile(message));
            saveMessage.addStyleName(CommonResources.INSTANCE.css().errorMessage());
        } else {
            saveMessage.setText(MainMessages.INSTANCE.savedFile(message));
        }
    }

    @UiHandler("save")
    public void copyClicked(ClickEvent click) {
        String url = worker.getImageDataURL();
        presenter.saveImage(url);
    }

    private void sizeCanvas(Canvas canvas) {
        canvas.setStyleName(CommonResources.INSTANCE.css().canvas());
        canvas.setHeight("500px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(500);
        canvas.setCoordinateSpaceWidth(500);
    }

}