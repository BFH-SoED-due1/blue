package ch.bfh.blue.UI;

import java.awt.Container;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

/**
 * After selecting a room, this view will show all the timeframes in which the
 * room is free.
 *
 * @author SRS-Team
 *
 */

public class ReservationBySelectedRoomView extends VerticalLayout implements View {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Navigator navigator;
	private Controller controller;

	// Constants
	private static final String DATE_FORMAT = "dd.MM.yy kk:mm";
	private static final String RESERVATION_SUCCESS = "You have successfully made a reservation.";
	private static final String RESERVATION_NULL_VALUE_ERROR = "One or more Values were NULL, no reservation was made!";
	private static final String RESERVATION_OVERLAPING_ERROR = "Overlaping with another reservation, No reservation made!";
	private static final String LOGIN_ERROR_NOT_LOGGED_IN = "You got redirected to the homepage, since no user was logged in.";

	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	private final HorizontalLayout calZoomHL = new HorizontalLayout();
	private final HorizontalLayout dateFieldHL = new HorizontalLayout();
	private final VerticalLayout reservationVL = new VerticalLayout();

	// Labels and Components
	private final Label heading = new Label();
	private final Label dateFieldCaption = new Label();
	Calendar cal = new Calendar();
	private DateField startDateField = new DateField();
	private DateField endDateField = new DateField();
	private TextField resTitle = new TextField();
	private Date startDate;
	private Date endDate;
	private Notification notif = new Notification("", Notification.Type.WARNING_MESSAGE);
	private Space currentSpace;
	private List<Reservation> reservations;
	private Person currentPerson;

	// Buttons
	private final Button logoutBtn = new Button("Logout");
	private final Button backBtn = new Button("Back");
	private final Button btnDay = new Button("Day view");
	private final Button btnWeek = new Button("Week view");
	private final Button btnMonth = new Button("Month view");
	private final Button reservationBtn = new Button("Make reservation");

	public ReservationBySelectedRoomView(Controller contr) {
		controller = contr;
		configureUI();
		configureButtons();
		configureCal();
		configureDatePickers();
		for (Component c : new Component[] { heading, calZoomHL, cal, dateFieldCaption, reservationVL, navBtnHL })
			this.addComponent(c);
	}

	/**
	 * configure all the settings for the different UI components
	 */
	private void configureUI() {
		this.setSpacing(true);
		heading.setValue("Available timeframes for the selected room:");
		dateFieldCaption.setValue("Select the timeframe for your reservation.");

		// ***addnig demo event to calendar
		endDate = addTimeToDate(new Date(), java.util.Calendar.HOUR, 1);
		cal.addEvent(new BasicEvent("event", "eventdiscription", new Date(), endDate));

		dateFieldHL.addComponents(startDateField, endDateField);
		dateFieldHL.setSpacing(true);
		reservationVL.addComponents(resTitle, dateFieldHL, reservationBtn);
		reservationVL.setSpacing(true);

		resTitle.setInputPrompt("Enter title");
	}

	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons() {
		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});

		backBtn.addClickListener(e -> {
			navigator.navigateTo("availableSpaces");
		});
		navBtnHL.addComponents(backBtn, logoutBtn);
		navBtnHL.setSpacing(true);

		btnDay.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				cal.setStartDate(new Date());
				cal.setEndDate(new Date());
			}
		});
		btnWeek.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				cal.setStartDate(new Date());
				cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.DAY_OF_WEEK, 7));
			}
		});
		btnMonth.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				cal.setStartDate(new Date());
				cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.MONTH, 1));
			}
		});

		reservationBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String t = resTitle.getValue();
				startDate = startDateField.getValue();
				endDate = endDateField.getValue();
				Space spc = (Space) getSession().getAttribute("space");
				Person p = (Person) getSession().getAttribute("user");
				boolean nullLock = t != null && p != null && startDate != null && endDate != null && spc != null;
				if (nullLock) {
					if (isNotOverlaping()) {
						controller.createReservation(t, p, startDate, endDate, spc);
						cal.addEvent(new BasicEvent(t, "sgd", startDate, endDate));
						notif.setCaption(RESERVATION_SUCCESS + " " + resTitle.getValue());
						notif.show(Page.getCurrent());
					} else {
						notif.setCaption(RESERVATION_OVERLAPING_ERROR);
						notif.show(Page.getCurrent());
					}
				} else {
					notif.setCaption(RESERVATION_NULL_VALUE_ERROR);
					notif.show(Page.getCurrent());
				}
			}
		});

		makeReservationBtnDisabled();
		calZoomHL.addComponents(btnDay, btnWeek, btnMonth);
		calZoomHL.setSpacing(true);
	}

	/**
	 * this method makes sure different time frames of the same space can't
	 * overlap returns true if there is NO overlapping
	 */
	private boolean isNotOverlaping() {
		reservations = currentSpace.getReservations();
		boolean overlapLock = false;
		if (reservations.isEmpty()) {
			overlapLock = true;
		} else {
			for (Reservation res : reservations) {
				Date st = res.getStStamp();
				Date en = res.getEnStamp();
				if (startDate.compareTo(en) >= 0 || endDate.compareTo(st) <= 0) {
					overlapLock = true;
				} else {
					overlapLock = false;
				}
			}
		}
		return overlapLock;
	}

	/**
	 * this method provides the function to disable the "reservationBtn" if no
	 * title is written in the "title" TextField
	 */
	private void makeReservationBtnDisabled() {
		resTitle.addTextChangeListener(te -> {
			if (te.getText().equals("") || te.getText().equals(null)) {
				reservationBtn.setEnabled(false);
			} else {
				reservationBtn.setEnabled(true);
			}
		});
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
	 * configurations of the calendar are done here
	 */
	private void configureCal() {
		cal.setWidth("800px");
		cal.setHeight("500px");
		cal.setStartDate(new Date());
		cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.DAY_OF_WEEK, 7));
		cal.setReadOnly(true);
	}

	/**
	 * fills the calendar with all the reservations done for the curretn space
	 */
	public void fillCalendar() {
		reservations = currentSpace.getReservations();
		for (Reservation res : reservations) {
			String t = res.getTitle();
			Date st = res.getStStamp();
			Date en = res.getEnStamp();
			cal.addEvent(new BasicEvent(t, "sgd", st, en));
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
	 * is called upon entering this view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		if(isLoggedIn()){
		startDateField.setValue(new Date());
		endDateField.setValue(addTimeToDate(startDateField.getValue(), java.util.Calendar.HOUR, 1));
		resTitle.clear();
		reservationBtn.setEnabled(false);
		currentSpace = (Space) getSession().getAttribute("space");
		heading.setValue("Available timeframes for the selected room: " + currentSpace);
		cal.setContainerDataSource(new BeanItemContainer<BasicEvent>(BasicEvent.class));
		fillCalendar();
		}
	}

}
