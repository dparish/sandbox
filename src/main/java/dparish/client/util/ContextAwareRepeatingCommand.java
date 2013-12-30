package dparish.client.util;

import com.google.gwt.core.client.Scheduler;
import dparish.client.presenter.MainPresenter;
import dparish.client.view.Page;

/**
 * @author dparish
 */
public abstract class ContextAwareRepeatingCommand implements Scheduler.RepeatingCommand {


    private Page initialPage;
    private String commandName = "";

    public ContextAwareRepeatingCommand() {
        initialPage = MainPresenter.currentPage;
    }

    public ContextAwareRepeatingCommand(String commandName) {
        this();
        this.commandName = commandName;
    }

    /**
     * Returns true if the RepeatingCommand should be invoked again.
     */
    @Override
    public boolean execute() {
        if (initialPage != MainPresenter.currentPage) {
            Logger.error("No longer on page " + initialPage + " will not run " + commandName);
            return false;
        }
        return run();
    }

    public abstract boolean run();
}
