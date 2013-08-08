package dparish.client.view.calendar;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.MonthSelector;

/**
 * @author dparish
 *
 * Ripped right out of DefaultMonthSelector so I could style it better.
 */
public class CustomMonthSelector extends MonthSelector {

    private Label backwards;
    private Label forwards;
    private Grid grid;

    /**
     * Constructor.
     */
    public CustomMonthSelector() {
        super();
    }

    /**
     * Returns the button for moving to the previous month.
     */
    public Element getBackwardButtonElement() {
        return backwards.getElement();
    }

    /**
     * Returns the button for moving to the next month.
     */
    public Element getForwardButtonElement() {
        return forwards.getElement();
    }

    @Override
    protected void refresh() {
        String formattedMonth = getModel().formatCurrentMonth();
        grid.setText(0, 1, formattedMonth);
    }

    @Override
    protected void setup() {
        // Set up backwards.
        backwards = new Label();
        backwards.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addMonths(-1);
            }
        });

        backwards.setText("<");
        backwards.setStyleName(CalendarResources.INSTANCE.calendarCss().nextButton());

        forwards = new Label();
        forwards.setText(">");
        forwards.setStyleName(CalendarResources.INSTANCE.calendarCss().nextButton());
        forwards.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addMonths(+1);
            }
        });

        // Set up grid.
        grid = new Grid(1, 3);
        grid.setWidget(0, 0, backwards);
        grid.setWidget(0, 2, forwards);

        HTMLTable.CellFormatter formatter = grid.getCellFormatter();
        formatter.setStyleName(0, 1, CalendarResources.INSTANCE.calendarCss().monthDisplay());
        formatter.setWidth(0, 0, "1");
        formatter.setWidth(0, 1, "100%");
        formatter.setWidth(0, 2, "1");
        grid.setStyleName(CalendarResources.INSTANCE.calendarCss().monthHeading());
        initWidget(grid);
    }

    @Override
    protected void addMonths(int numMonths) {
        super.addMonths(numMonths);
    }
}
