package dparish.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author dparish
 */
public class MainViewImpl extends Composite implements MainView {
    interface MainViewImplUiBinder extends UiBinder<DockLayoutPanel, MainViewImpl> {
    }

    private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

    @UiField
    SimplePanel content;

    private Presenter presenter;

    @Inject
    public MainViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getContentPanel() {
        return content;
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