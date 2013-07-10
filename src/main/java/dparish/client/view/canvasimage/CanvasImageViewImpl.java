package dparish.client.view.canvasimage;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;
import dparish.client.widgets.NativeFile;

/**
 * @author David Parish dparish
 */
public class CanvasImageViewImpl extends Composite implements CanvasImageView {
    interface CanvasImageViewImplUiBinder extends UiBinder<FlowPanel, CanvasImageViewImpl> {
    }

    private static CanvasImageViewImplUiBinder ourUiBinder = GWT.create(CanvasImageViewImplUiBinder.class);

    @UiField
    SimplePanel canvasContainer;
    @UiField
    SimplePanel imageContainer;

    @UiField
    FileUpload uploadFile;

    private Presenter presenter;
    private Canvas canvas;
    private ImageUploadWorker worker;

    @Inject
    public CanvasImageViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        uploadFile.getElement().setId("iconUploadWidget");
        canvas = Canvas.createIfSupported();
        canvas.setStyleName(CommonResources.INSTANCE.css().canvas());
        canvas.setHeight("500px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(500);
        canvas.setCoordinateSpaceWidth(500);
        canvasContainer.setWidget(canvas);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        worker = new ImageUploadWorker(canvas, presenter);
    }

    @Override
    public void renderImage(NativeFile file, String data) {
        final Image image = new Image();
        image.setUrl(data);
        imageContainer.setWidget(image);
        image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                ImageElement imageElement = ImageElement.as(image.getElement());
                worker.clear();
                worker.getContext().drawImage(imageElement, 0, 0);
            }
        });
    }

    @UiHandler("uploadFile")
    public void onUploadFileChanged(ChangeEvent event) {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                worker.handleUpload(getNativeFile());
            }
        });
    }

    private native NativeFile getNativeFile() /*-{
        return $wnd.document.getElementById('iconUploadWidget').files[0];
    }-*/;
}