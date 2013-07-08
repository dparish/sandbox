package dparish.client.view;

import com.google.gwt.user.client.ui.Widget;

public interface View {

    /**
     * Get the widget to use to display this view.
     *
     * @return The widget.
     */
    Widget asWidget();
}