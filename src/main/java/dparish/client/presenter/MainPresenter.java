package dparish.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.MainView;
import dparish.client.view.Page;

/**
 * @author dparish
 */
@Singleton
public class MainPresenter extends BasePresenter<MainView> implements MainView.Presenter {

    private WelcomePresenter welcomePresenter;
    private BasicCanvasPresenter basicCanvasPresenter;
    private TankPresenter tankPresenter;
    private CanvasImagePresenter canvasImagePresenter;
    private WindowBoxPresenter windowBoxPresenter;
    private ImageCropPresenter imageCropPresenter;
    private CalendarPresenter calendarPresenter;

    private static String PAGE_COOKIE = "selectedPage";

    @Inject
    public MainPresenter(MainView view, WelcomePresenter welcomePresenter, BasicCanvasPresenter basicCanvasPresenter,
                         TankPresenter tankPresenter, CanvasImagePresenter canvasImagePresenter,
                         WindowBoxPresenter windowBoxPresenter, ImageCropPresenter imageCropPresenter,
                         CalendarPresenter calendarPresenter) {
        super(view);
        this.welcomePresenter = welcomePresenter;
        this.basicCanvasPresenter = basicCanvasPresenter;
        this.tankPresenter = tankPresenter;
        this.canvasImagePresenter = canvasImagePresenter;
        this.windowBoxPresenter = windowBoxPresenter;
        this.imageCropPresenter = imageCropPresenter;
        this.calendarPresenter = calendarPresenter;

        view.setPresenter(this);
        onPageSelected(getPageFromCookie());
    }
    @Override
    public void onPageSelected(Page page) {
        setCookie(page);
        switch (page) {
            case WELCOME:
                welcomePresenter.go(view().getContentPanel());
                break;
            case BASIC_CANVAS:
                basicCanvasPresenter.go(view().getContentPanel());
                break;
            case TANK:
                tankPresenter.go(view().getContentPanel());
                break;
            case CANVAS_IMAGE:
                canvasImagePresenter.go(view().getContentPanel());
                break;
            case WINDOW_BOX:
                windowBoxPresenter.go(view().getContentPanel());
                break;
            case IMAGE_CROP:
                imageCropPresenter.go(view().getContentPanel());
                break;
            case CALENDAR:
                calendarPresenter.go(view().getContentPanel());
                break;
        }
    }

    private void setCookie(Page page) {
        Cookies.setCookie(PAGE_COOKIE, page.name());
    }

    private Page getPageFromCookie() {
        String pageName = Cookies.getCookie(PAGE_COOKIE);
        if (pageName == null) {
            return Page.WELCOME;
        }
        Page page =  Page.valueOf(pageName);
        if (page == null) {
            GWT.log("page should not be null here");
            return Page.WELCOME;
        }
        return page;
    }
}
