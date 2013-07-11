package dparish.client.view.imagecrop;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;

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
    Image image;

    private Presenter presenter;

    private Canvas canvas;

    private ImageCropWorker worker;

    @Inject
    public ImageCropImpl() {
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
    public void setPresenter(Presenter p) {
        this.presenter = p;
        worker = new ImageCropWorker(canvas);
        worker.render(image);
    }
}