/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

/**
 * This view provides the user with the options to either choose a room and get
 * the available time frames for the room or to select a time frame and get all
 * rooms that are free in this time frame
 *
 * @author SRS-Team
 *
 */

public class AvailableSpacesView extends VerticalLayout implements View {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Navigator navigator;
	private Controller controller;

	// Constants
	private static final String DATE_FORMAT = "dd.MM.yy kk:mm";
	private static final String SELECT_ERROR_NO_ROOM_SELECTED = "Pleas select a room before proceeding.";
	private static final String LOGIN_ERROR_NOT_LOGGED_IN = "You got redirected to the homepage, since no user was logged in.";

	// Layouts which contain components
	private final HorizontalLayout selectHL = new HorizontalLayout();
	private final HorizontalLayout dateHL = new HorizontalLayout();

	// Labels and Components
	private final ComboBox roomsCB = new ComboBox();
	private final Label dateCaption = new Label();
	private final Label roomCaption = new Label();
	private final DateField startDateField = new DateField();
	private final DateField endDateField = new DateField();
	private Notification notif = new Notification("", Notification.Type.WARNING_MESSAGE);
	private Grid grid = new Grid("Your current Reservations:");
	private Person currentPerson;
	private List<Reservation> reservations;

	// Buttons
	private final Button selectBtn = new Button("Select");
	private final Button logoutBtn = new Button("Logout");
	private final Button availableBtn = new Button("Search available rooms");

	public AvailableSpacesView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { grid, roomCaption, selectHL, dateCaption, dateHL, logoutBtn })
			this.addComponent(c);
		configureUI();
		configureButtons();
		configureDatePickers();
	}

	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI() {
		this.setSpacing(true);
		roomsCB.setFilteringMode(FilteringMode.CONTAINS);
		roomsCB.setInputPrompt("select a space");
		selectHL.addComponents(roomsCB, selectBtn);
		selectHL.setMargin(true);
		selectHL.setSpacing(true);
		roomCaption.setValue("Select a room for which you would like to see the available timeframes.");
		dateCaption.setValue("Select a timeframe in which you would like to see the available rooms.");
		dateHL.addComponents(startDateField, endDateField, availableBtn);
		dateHL.setSpacing(true);
		dateHL.setMargin(true);
		notif.setDelayMsec(4000);
		configureReservationGrid();

	}

	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons() {
		selectBtn.addClickListener(e -> {
			if (roomsCB.getValue() != null) {
				System.out.println(roomsCB.getValue().getClass());
				getSession().setAttribute("space", roomsCB.getValue());
				navigator.navigateTo("reservationBySelectedRoom");
			} else {
				notif.setCaption(SELECT_ERROR_NO_ROOM_SELECTED);
				notif.show(Page.getCurrent());
			}
		});

		availableBtn.addClickListener(e -> {
			getSession().setAttribute("startDate", startDateField.getValue());
			getSession().setAttribute("endDate", endDateField.getValue());
			navigator.navigateTo("reservationBySelectedTime");
		});

		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});

	}

	/**
	 * fills the combo box with rooms
	 */
	private void fillComboBox() {
		roomsCB.setContainerDataSource(new BeanItemContainer<>(Space.class, controller.getAllspaces()));
	}

	/**
	 * configure DateFields and add them to the dateLayout
	 */
	private void configureDatePickers() {
		startDateField.setResolution(Resolution.MINUTE);
		startDateField.setDateFormat(DATE_FORMAT);
		startDateField.setValue(new Date());

		endDateField.setResolution(Resolution.MINUTE);
		endDateField.setDateFormat(DATE_FORMAT);
		endDateField.setValue(addTimeToDate(startDateField.getValue(), java.util.Calendar.HOUR, 1));
		dontAllowEndBevoreStart();
	}

	/**
	 * this method makes sure that the end date picker can't pick a date that is
	 * sooner than the one selected by the start date picker
	 */
	private void dontAllowEndBevoreStart() {
		startDateField.addValueChangeListener(e -> {
			if (startDateField.getValue().compareTo(endDateField.getValue()) > 0) {
				endDateField.setValue(addTimeToDate(startDateField.getValue(), java.util.Calendar.HOUR, 1));
			}
		});

		endDateField.addValueChangeListener(e -> {
			if (endDateField.getValue().compareTo(startDateField.getValue()) < 0) {
				startDateField.setValue(addTimeToDate(endDateField.getValue(), java.util.Calendar.HOUR, -1));
			}
		});
	}

	/**
	 * adds a certain amount of a time unit(hour, week, day, etc.) to an
	 * existing date
	 */
	private Date addTimeToDate(Date d, int f, int x) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(f, x);
		return gc.getTime();
	}

	/**
	 * configure the grid which shows all the reservations the logged in person
	 * has already made
	 */
	private void configureReservationGrid() {
		grid.setWidth("800");
		grid.setHeight("300");
		grid.setReadOnly(true);
	}

	/**
	 * loads all the reservations of the current user into the grid
	 */
	private void refreshGrid() {
		reservations = currentPerson.getReservations();
		grid.setContainerDataSource(new BeanItemContainer<>(Reservation.class, reservations));
		grid.setColumnOrder("title", "space", "stStamp", "enStamp");
		if (grid.getColumn("owner") != null) {
			grid.removeColumn("owner");
		}

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
		startDateField.setValue(new Date());
		endDateField.setValue(addTimeToDate(startDateField.getValue(), java.util.Calendar.HOUR, 1));
		fillComboBox();
		refreshGrid();
		}
	}

}
