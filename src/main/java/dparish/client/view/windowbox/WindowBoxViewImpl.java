package dparish.client.view.windowbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;
import dparish.client.widgets.SquareWindowPane;

/**
 * @author dparish
 */
public class WindowBoxViewImpl extends Composite implements WindowBoxView {
    interface WindowBoxViewImplUiBinder extends UiBinder<FlowPanel, WindowBoxViewImpl> {
    }

    private static WindowBoxViewImplUiBinder ourUiBinder = GWT.create(WindowBoxViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    FlowPanel content;


    @Inject
    public WindowBoxViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        SquareWindowPane draggablePanel = new SquareWindowPane(10,10,100, 500, 500);
        draggablePanel.setStyleName(CommonResources.INSTANCE.css().draggablePanel());
        content.add(draggablePanel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}