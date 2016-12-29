package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;

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

	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	private final HorizontalLayout calZoomHL = new HorizontalLayout();
	private Date startDate;
	private Date endDate;

	// Labels and Components
	private final Label heading = new Label();
	Calendar cal = new Calendar();

	// Buttons
	private final Button logoutBtn = new Button("Logout");
	private final Button backBtn = new Button("Back");
	private final Button btnDay = new Button("Day view");
	private final Button btnWeek = new Button("Week view");
	private final Button btnMonth = new Button("Month view");

	public ReservationBySelectedRoomView(Controller contr) {
		// controller = contr;
		for (Component c : new Component[] { heading, calZoomHL, cal, navBtnHL })
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	/**
	 * configure all the settings for the different UI components
	 */
	private void configureUI() {
		this.setSpacing(true);
		heading.setValue("Available timeframes for the selected room:");
		
		//***addnig demo event to calendar
		endDate = addTimeToDate(new Date(), java.util.Calendar.HOUR, 1);
		cal.addEvent(new BasicEvent("event", "eventdiscription", new Date(), endDate));
		
		configureCal();
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
		
		calZoomHL.addComponents(btnDay,btnWeek,btnMonth);
		calZoomHL.setSpacing(true);
	}
	
	/**
	 * configurations of the calendar are done here
	 */
	private void configureCal(){
		cal.setWidth("800px");
		cal.setHeight("600px");
		cal.setStartDate(new Date());
		cal.setEndDate(addTimeToDate(new Date(), java.util.Calendar.DAY_OF_WEEK, 7));
		//cal.setReadOnly(true);
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
		navigator = event.getNavigator();
		// list times for the selected room
		// list rooms for the selected time

	}

}
