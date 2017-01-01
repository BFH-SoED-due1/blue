package ch.bfh.blue.UI;

import java.util.Date;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

/**
 * After selecting a timeframe, this view will show all the available rooms for
 * this timeframe
 * 
 * @author SRS-Team
 *
 */

public class ReservationBySelectedTimeView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Navigator navigator;
	Controller controller;

	// Constants
	private static final String RESERVATION_SUCCESS = "You have successfully made a reservation.";
	private static final String RESERVATION_NULL_VALUE_ERROR = "One or more Values were NULL, no reservation was made!";
	private static final String LOGIN_ERROR_NOT_LOGGED_IN = "You got redirected to the homepage, since no user was logged in.";

	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	private final HorizontalLayout resHL = new HorizontalLayout();
	private final VerticalLayout dateVL = new VerticalLayout();

	// Labels and Components
	String dates = new String();
	private Date startDate;
	private Date endDate;
	private final Label heading = new Label();
	private final Label startDateLabel = new Label();
	private final Label endDateLabel = new Label();
	private final Grid grid = new Grid();
	private final TextField restitle = new TextField();
	private Notification notif = new Notification("", Notification.Type.WARNING_MESSAGE);
	List<Space> spaces;
	boolean gridLock = false;
	boolean titleLock = false;
	private Space currentSpace;
	private List<Reservation> reservations;
	private Person currentPerson;

	// Buttons
	private final Button reservationBtn = new Button("Make reservation");
	private final Button logoutBtn = new Button("Logout");
	private final Button backBtn = new Button("Back");

	public ReservationBySelectedTimeView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { heading, grid, resHL, navBtnHL })
			this.addComponent(c);
		notif.setDelayMsec(4000);
		configureUI();
		configureButtons();
		makeReservationBtnDisabled();
	}

	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI() {
		this.setSpacing(true);
		heading.setValue("Available rooms for the selected timeframe:");
		restitle.setValue("");
		restitle.setInputPrompt("Enter title");
		resHL.addComponents(restitle, reservationBtn, dateVL);
		resHL.setSpacing(true);
		dateVL.addComponents(startDateLabel, endDateLabel);
		configureGrid();
	}

	/**
	 * configurations of the grid are done here
	 */
	private void configureGrid() {
		grid.setWidth("300px");
		grid.setHeight("400px");
	}

	/**
	 * loads free rooms from the db and refreshes the grid
	 */
	private void refreshGrid() {
		spaces = controller.getSpaceOnTime(startDate, endDate);
		grid.setContainerDataSource(new BeanItemContainer<>(Space.class, spaces));
	}

	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons() {

		reservationBtn.addClickListener(e -> {
			Person p = (Person) getSession().getAttribute("user");
			Space spc = (Space) grid.getSelectedRow();
			String t = restitle.getValue();
			if (t != null && p != null && startDate != null && endDate != null && spc != null) {
				controller.createReservation(t, p, startDate, endDate, spc);
				notif.setCaption(RESERVATION_SUCCESS + " " + restitle.getValue());
				notif.show(Page.getCurrent());
				refreshGrid();
			} else {
				notif.setCaption(RESERVATION_NULL_VALUE_ERROR);
				notif.show(Page.getCurrent());
			}

		});

		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});

		backBtn.addClickListener(e -> {
			navigator.navigateTo("availableSpaces");
		});

		navBtnHL.addComponents(backBtn, logoutBtn);
		navBtnHL.setSpacing(true);
	}

	/**
	 * this method provides the function to disable the "reservationBtn" if no
	 * title is written in the "title" TextField or no selection is made in the
	 * grid
	 */
	private void makeReservationBtnDisabled() {
		grid.addSelectionListener(ge -> {
			if (ge.getSelected().isEmpty()) {
				reservationBtn.setEnabled(false);
				gridLock = false;
			} else {
				gridLock = true;
				if (gridLock && titleLock) {
					reservationBtn.setEnabled(true);
				}
			}
		});

		restitle.addTextChangeListener(te -> {
			if (te.getText().equals("") || te.getText().equals(null)) {
				reservationBtn.setEnabled(false);
				titleLock = false;
			} else {
				titleLock = true;
				if (gridLock && titleLock) {
					reservationBtn.setEnabled(true);
				}
			}
		});
	}

	/**
	 * this method makes sure different time frames of the same space can't
	 * overlap returns true if there is NO overlapping
	 */
	private boolean isNotOverlaping() {
		reservations = currentSpace.getReservations();
		boolean overlapLock = false;
		for (Reservation res : reservations) {
			Date st = res.getStStamp();
			Date en = res.getEnStamp();
			if (startDate.compareTo(en) >= 0 && endDate.compareTo(st) <= 0) {
				overlapLock = true;
			} else {
				overlapLock = false;
			}
		}
		return overlapLock;
	}

	/**
	 * checks if a user is logged in
	 * returns true if someone is logged in
	 * returns false if no one is logged in
	 */
	private boolean isLoggedIn() {
		currentPerson = (Person) getSession().getAttribute("user");
		System.out.println(currentPerson);
		if (currentPerson == null) {
			notif.setCaption(LOGIN_ERROR_NOT_LOGGED_IN);
			notif.show(Page.getCurrent());
			navigator.navigateTo("home");
			return false;
		} 
		return true;
	}

	/**
	 * called upon entering the view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		if(isLoggedIn()){
		currentSpace = (Space) getSession().getAttribute("space");
		startDate = (Date) getSession().getAttribute("startDate");
		endDate = (Date) getSession().getAttribute("endDate");
		refreshGrid();
		if (grid.getColumn("reservations") != null) {
			grid.removeColumn("reservations");
		}
		grid.deselectAll();
		reservationBtn.setEnabled(false);
		startDateLabel.setValue("From: " + startDate);
		endDateLabel.setValue("To: " + endDate);
		}
	}

}
