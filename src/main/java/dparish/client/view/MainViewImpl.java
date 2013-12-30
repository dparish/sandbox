package dparish.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import dparish.client.resources.CommonResources;
import dparish.client.resources.MainMessages;

/**
 * @author dparish
 */
public class MainViewImpl extends Composite implements MainView {
    interface MainViewImplUiBinder extends UiBinder<DockLayoutPanel, MainViewImpl> {
    }

    private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

    @UiField
    SimplePanel content;

    @UiField
    FlowPanel leftNav;

    private Presenter presenter;

    @Inject
    public MainViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        drawNav();
        CommonResources.INSTANCE.css().ensureInjected();
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

    @Override
    public String getCookie(String cookieName) {
        return Cookies.getCookie(cookieName);
    }

    @Override
    public void setCookie(String cookieName, String value) {
        Cookies.setCookie(cookieName, value);
    }

    @Override
    public void setPageSelected(Page page) {
        this.ensureDebugId(page.name());
    }

    private void drawNav() {
        for (final Page page:Page.values()) {
            Label navItem = new Label(MainMessages.INSTANCE.navLabel(page));
            navItem.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    presenter.onPageSelected(page);
                }
            });
            navItem.setStyleName(CommonResources.INSTANCE.css().button());
            leftNav.add(navItem);
        }
    }
}