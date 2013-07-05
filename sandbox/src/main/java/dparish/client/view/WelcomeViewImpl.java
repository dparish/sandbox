package dparish.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class WelcomeViewImpl extends Composite implements WelcomeView {
    interface WelcomeViewImplUiBinder extends UiBinder<FlowPanel, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder ourUiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    private Presenter presenter;

    @Inject
    public WelcomeViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    /**
     * Get the widget to use to display this view.
     *
     * @return The widget.
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void setPresenter(Presenter p) {
        presenter = p;
    }
}