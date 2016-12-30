package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

/**
 * This view provides the user with the options to either choose a room and get
 * the available time frames for the room
 * or to select a time frame and get all rooms that are free in this time frame
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
	
	//Constants
	private static final String DATE_FORMAT = "dd.MM.yy kk:mm";
	
	//Layouts which contain components
	private final HorizontalLayout selectHL = new HorizontalLayout();
	private final HorizontalLayout dateHL = new HorizontalLayout();
	
	//Labels and Components
	private final ComboBox roomsCB = new ComboBox();
	private final Label dateCaption = new Label();
	private final Label roomCaption = new Label();
	private final DateField startDate = new DateField();
	private final DateField endDate = new DateField();
	
	//Buttons
	private final Button selectBtn = new Button("Select");
	private final Button logoutBtn = new Button("Logout");
	private final Button availableBtn = new Button("Search available rooms");
	

	public AvailableSpacesView(Controller contr){
		controller = contr;
		for (Component c : new Component[] {roomCaption, selectHL, dateCaption, dateHL, logoutBtn})
			this.addComponent(c);
		configureUI();
		configureButtons();
		configureDatePickers();
	}
	
	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI(){
		this.setSpacing(true);
		roomsCB.setFilteringMode(FilteringMode.CONTAINS);
		roomsCB.setInputPrompt("select a space");
		selectHL.addComponents(roomsCB, selectBtn);
		selectHL.setMargin(true);
		selectHL.setSpacing(true);
		roomCaption.setValue("Select a room for which you would like to see the available timeframes.");
		dateCaption.setValue("Select a timeframe in which you would like to see the available rooms.");
		
		dateHL.addComponents(startDate, endDate, availableBtn);
		dateHL.setSpacing(true);
		dateHL.setMargin(true);
		
	}
	
	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons(){
		selectBtn.addClickListener(e -> {
			System.out.println(roomsCB.getValue().getClass());
			controller.setCurrentSpace((Space)roomsCB.getValue());
			System.out.println(controller.getCurrentSpace());
			navigator.navigateTo("reservationBySelectedRoom");
		});
		
		availableBtn.addClickListener(e -> {
			controller.setCurrentDate(startDate.getValue(), endDate.getValue());
			navigator.navigateTo("reservationBySelectedTime");
		});
		
		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
		
	}	
	
	
	/**
	 * fills the combo box with rooms
	 */
	private void fillComboBox(){
		roomsCB.setContainerDataSource(new BeanItemContainer<>(Space.class, controller.getAllspaces()));
	}
	
	/**
	 * configure DateFields and add them to the dateLayout
	 */
	private void configureDatePickers(){		
		startDate.setResolution(Resolution.MINUTE);
		startDate.setDateFormat(DATE_FORMAT);
		startDate.setValue(new Date()); // Set the date and time to present

		endDate.setResolution(Resolution.MINUTE);
		endDate.setDateFormat(DATE_FORMAT);
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date());
		gregCal.add(java.util.Calendar.HOUR, 1);
		endDate.setValue(gregCal.getTime()); // Set the date and time to present + 1h
	}
	
	
	/**
	 * called upon entering the view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();		
		fillComboBox();
	}

}
