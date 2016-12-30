package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;

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
	
	//Constants
		private static final String DATE_FORMAT = "dd.MM.yy kk:mm";


	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	private final HorizontalLayout calZoomHL = new HorizontalLayout();
	private final HorizontalLayout dateFieldHL = new HorizontalLayout();
	Date startDate;
	Date endDate;

	// Labels and Components
	private final Label heading = new Label();
	private final Label dateFieldCaption = new Label();
	Calendar cal = new Calendar();
	private DateField startDateField = new DateField();
	private DateField endDateField = new DateField();
	private TextField resTitle = new TextField();

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
		for (Component c : new Component[] { heading, calZoomHL, cal, dateFieldCaption, dateFieldHL, navBtnHL })
			this.addComponent(c);
	}

	/**
	 * configure all the settings for the different UI components
	 */
	private void configureUI() {
		this.setSpacing(true);
		heading.setValue("Available timeframes for the selected room:");
		dateFieldCaption.setValue("Select the timeframe for your reservation.");
		
		//***addnig demo event to calendar
		endDate = addTimeToDate(new Date(), java.util.Calendar.HOUR, 1);
		cal.addEvent(new BasicEvent("event", "eventdiscription", new Date(), endDate));
		
		dateFieldHL.addComponents(startDateField, endDateField, resTitle, reservationBtn);
		dateFieldHL.setSpacing(true);
		dateFieldHL.setMargin(true);
		
		resTitle.setInputPrompt("Title of reservation");
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
			    public void buttonClick(ClickEvent event) {
			        cal.setStartDate(new Date());
			        cal.setEndDate(new Date());
			    }
			});
		btnWeek.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				cal.setStartDate(new Date());
				cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.DAY_OF_WEEK, 7));
			}
		});
		btnMonth.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				cal.setStartDate(new Date());
				cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.MONTH, 1));
			}
		});
		
		reservationBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String t = resTitle.getValue();
				Date st = startDateField.getValue();
				Date en = endDateField.getValue();
				Space spc = controller.getCurrentSpace();
				controller.createReservation(t, controller.getCurrentPerson(), st, en, spc);
						cal.addEvent(new BasicEvent(t, "sgd", st, en));
			}
		});
		
		
		calZoomHL.addComponents(btnDay,btnWeek,btnMonth);
		calZoomHL.setSpacing(true);
	}
	
	/**
	 * configure DateFields and add them to the dateLayout
	 */
	private void configureDatePickers(){		
		startDateField.setResolution(Resolution.MINUTE);
		startDateField.setDateFormat(DATE_FORMAT);
		startDateField.setValue(new Date()); // Set the date and time to present

		endDateField.setResolution(Resolution.MINUTE);
		endDateField.setDateFormat(DATE_FORMAT);
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date());
		gregCal.add(java.util.Calendar.HOUR, 1);
		endDateField.setValue(gregCal.getTime()); // Set the date and time to present + 1h
	}
	
	/**
	 * configurations of the calendar are done here
	 */
	private void configureCal(){
		cal.setWidth("800px");
		cal.setHeight("600px");
		cal.setStartDate(new Date());
		cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.DAY_OF_WEEK, 7));
		cal.setReadOnly(true);
	}
	
	/**
	 * adds a certain amount of a time unit(hour, week, day, etc.) to an existing date
	 */
	private Date addTimeToDate(Date d, int f, int x ){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(f, x);
		return gc.getTime();
	}

	/**
	 * is called upon entering this view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		resTitle.clear();
		navigator = event.getNavigator();
		heading.setValue("Available timeframes for the selected room: "+controller.getCurrentSpace());
	}

}
