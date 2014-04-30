package dparish.client.view.directory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

/**
 * Created by david.parish on 4/29/14.
 */
public class DirectoryViewImpl extends Composite implements DirectoryView {
    interface DirectoryViewImplUiBinder extends UiBinder<FlowPanel, DirectoryViewImpl> {
    }

    private static DirectoryViewImplUiBinder ourUiBinder = GWT.create(DirectoryViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    SimplePanel addPersonContainer;

    @Inject
    public DirectoryViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public HasWidgets getAddPersonContainer() {
        return addPersonContainer;
    }
}