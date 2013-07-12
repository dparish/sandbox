package dparish.client.view.imagecrop;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import dparish.client.resources.canvas.CanvasResources;
import dparish.client.view.basiccanvas.BaseCanvasWorker;
import dparish.client.widgets.SquareWindowPane;

/**
 * @author dparish
 */
public class ImageCropWorker extends BaseCanvasWorker {

    private ImageElement theImage;
    private Image sourceImage;
    private double aspectRatio = 0.0;
    private int biggestSide;
    private int smallestSide;
    private Integer canvasDisplayHeight = null;

    // The start x and start y are the x and y start points for the images. Used to show black bars.
    private int startX = 0;
    private int startY = 0;

    private Canvas hiddenCanvas;
    private Context2d hiddenContext;
    private SquareWindowPane.PositionInfo lastPosition;

    public ImageCropWorker(Canvas canvas, Canvas hiddenCanvas) {
        super(canvas);
        this.hiddenCanvas = hiddenCanvas;
        hiddenContext = hiddenCanvas.getContext2d();
    }

    public void render(final Image image) {
        sourceImage = image;
        image.setUrl(CanvasResources.INSTANCE.mini().getSafeUri());
        image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                theImage = ImageElement.as(image.getElement());
                handleImageSize();
                drawImageWithAlpha();
                drawSourceImage();
            }
        });
    }

    public void windowMoved(SquareWindowPane.PositionInfo positionInfo) {
        // First draw the image with a transparency
        // clear();
        drawImageWithAlpha();

        // The border is one pixel wide so we need to decrease the height by 2 and add one pixel to the left and top
        //positionInfo.height -= 1;
        positionInfo.left += 1;
        positionInfo.top += 1;

        // The canvas dimensions will be different from the div dimensions so we have to calculate our way out
        // of this
        if (canvasDisplayHeight == null) {
            canvasDisplayHeight = canvas.getElement().getOffsetHeight();
        }
        double heightRatio = (double) canvas.getCoordinateSpaceHeight() / canvasDisplayHeight;
        Double newLeft = (double) positionInfo.left * heightRatio;
        positionInfo.left = newLeft.intValue();
        Double newTop = (double) positionInfo.top * heightRatio;
        positionInfo.top = newTop.intValue();
        Double newHeight = (double) positionInfo.height * heightRatio;
        positionInfo.height = newHeight.intValue();

        context.drawImage(hiddenCanvas.getCanvasElement(), positionInfo.left, positionInfo.top, positionInfo.height, positionInfo.height,
                positionInfo.left, positionInfo.top, positionInfo.height, positionInfo.height);
        lastPosition = positionInfo;
    }

    public String getImageDataURL() {
        // Grab the portion of the image that is highlighted and draw it on the new context.
        clearHiddenCanvas();
        hiddenContext.drawImage(canvas.getCanvasElement(),  lastPosition.left, lastPosition.top,lastPosition.height,
                lastPosition.height,
                0,0,hiddenCanvas.getCoordinateSpaceHeight(), hiddenCanvas.getCoordinateSpaceWidth());
        String dataUrl = hiddenCanvas.toDataUrl("jpg");
        clearHiddenCanvas();
        drawSourceImage();
        return dataUrl;
    }

    private void handleImageSize() {
        aspectRatio = (double) sourceImage.getHeight() / sourceImage.getWidth();
        if (sourceImage.getHeight() > sourceImage.getWidth()) {
            biggestSide = sourceImage.getHeight();
            smallestSide = sourceImage.getWidth();
        } else {
            biggestSide = sourceImage.getWidth();
            smallestSide = sourceImage.getHeight();
        }
        biggestSide = (sourceImage.getHeight() > sourceImage.getWidth()) ? sourceImage.getHeight() : sourceImage.getWidth();
        canvas.setCoordinateSpaceHeight(biggestSide);
        canvas.setCoordinateSpaceWidth(biggestSide);
        hiddenCanvas.setCoordinateSpaceHeight(biggestSide);
        hiddenCanvas.setCoordinateSpaceWidth(biggestSide);
        if (aspectRatio < 1) {
            // height is the smaller side so the black bars are vertical (x changes y is zero)
            Double margin = (biggestSide * (1 - aspectRatio)) / 2;
            startY = margin.intValue();
            startX = 0;
        } else {
            double flippedAspect = (double) smallestSide / biggestSide;
            Double margin = (biggestSide * (1 - flippedAspect)) / 2;
            startX = margin.intValue();
            startY = 0;
        }
    }
    private void clearHiddenCanvas() {
        hiddenContext.clearRect(0,0,hiddenCanvas.getCoordinateSpaceHeight(),hiddenCanvas.getCoordinateSpaceWidth());
    }

    private void drawImageWithAlpha() {
        fillWithBlack(canvas);

        // Draw a grey rectangle behind image, this is so opacity won't bleed to image.
        // TODO: Should be exactly 50% grey.
        context.setFillStyle("white");
        GWT.log("image:" + startX + ":" +  startY+ ":" + sourceImage.getWidth()+ ":" + sourceImage.getHeight());
        context.fillRect(startX, startY, sourceImage.getWidth(), sourceImage.getHeight());


        context.setGlobalAlpha(.5);
        context.drawImage(theImage, startX, startY);
        context.setGlobalAlpha(1);
    }

    private void drawSourceImage() {
        fillWithBlack(hiddenCanvas);
        hiddenContext.drawImage(theImage,startX,startY);
    }

    private void fillWithBlack(Canvas canvas) {
        Context2d ctx = canvas.getContext2d();
        ctx.setFillStyle("black");
        ctx.fillRect(0,0,canvas.getCoordinateSpaceHeight(), canvas.getCoordinateSpaceWidth());
    }
}
