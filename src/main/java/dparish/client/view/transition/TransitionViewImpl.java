package dparish.client.view.transition;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import dparish.client.BrowserUtils;
import dparish.client.CssEventHandler;
import dparish.client.resources.MainMessages;

/**
 * @author dparish
 */
public class TransitionViewImpl extends Composite implements TransitionView {
    interface TransitionViewImplUiBinder extends UiBinder<FlowPanel, TransitionViewImpl> {
    }

    private static TransitionViewImplUiBinder ourUiBinder = GWT.create(TransitionViewImplUiBinder.class);

    interface Style extends CssResource {
        String hoverLabel();
        String clickToMoveRight();
        String clickToMoveLeft();
        String clickToMove();
    }

    @UiField
    Label hoverLabel;

    @UiField
    Label statusLabel;

    @UiField
    Label moveLabel;

    @UiField
    Style style;

    private Presenter presenter;

    private boolean isHoverOver = false;

    private boolean onLeft = true;

    @Inject
    public TransitionViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        BrowserUtils.registerTransitionHandler(hoverLabel.getElement(), new CssEventHandler() {
            @Override
            public void onCssEnd() {
                if (isHoverOver) {
                    statusLabel.setText(MainMessages.INSTANCE.hoverOverEnded());
                } else {
                    statusLabel.setText(MainMessages.INSTANCE.hoverOutEnded());
                }
            }
        });
        BrowserUtils.registerAnimationHandler(moveLabel.getElement(), new CssEventHandler() {
            @Override
            public void onCssEnd() {
                moveLabel.setText(MainMessages.INSTANCE.clickToMove());
            }
        });
        hoverLabel.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                isHoverOver = true;
            }
        });
        hoverLabel.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                isHoverOver = false;
            }
        });

    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("moveLabel")
    public void moveLabelClicked(ClickEvent evt) {
        moveLabel.removeStyleName(style.clickToMoveLeft());
        moveLabel.removeStyleName(style.clickToMoveRight());
        moveLabel.setText(MainMessages.INSTANCE.moving());
        if (onLeft) {
            moveLabel.addStyleName(style.clickToMoveRight());
        } else {
            moveLabel.addStyleName(style.clickToMoveLeft());
        }
        onLeft = !onLeft;
    }
}