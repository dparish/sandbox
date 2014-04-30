package dparish.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dparish.client.view.directory.DirectoryView;

/**
 * Created by david.parish on 4/29/14.
 */
@Singleton
public class DirectoryPresenter extends BasePresenter<DirectoryView> implements DirectoryView.Presenter {

    private AddPersonPresenter addPersonPresenter;

    @Inject
    public DirectoryPresenter(DirectoryView view, AddPersonPresenter addPersonPresenter) {
        super(view);
        view().setPresenter(this);
        this.addPersonPresenter = addPersonPresenter;
    }

    @Override
    public void go(HasWidgets container) {
        super.go(container);
        addPersonPresenter.go(view().getAddPersonContainer());
    }
}
