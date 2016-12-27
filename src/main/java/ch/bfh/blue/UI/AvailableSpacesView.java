package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class AvailableSpacesView extends CssLayout implements View {

	private Navigator navigator;
	
	//Layouts which contain components
	private final HorizontalLayout selectHL = new HorizontalLayout();
	private final HorizontalLayout dateHL = new HorizontalLayout();
	
	//Labels and Components
	private final Label label = new Label();
	private final ComboBox roomsCB = new ComboBox();
	private final DateField startDate = new DateField();
	private final DateField endDate = new DateField();
	
	//Buttons
	private final Button selectBtn = new Button("Select");
	private final Button logoutBtn = new Button("Logout");
	private final Button timeBtn = new Button("Search available rooms");
	

	public AvailableSpacesView(){
		for (Component c : new Component[] {label, selectHL, logoutBtn, dateHL})
			this.addComponent(c);
		configureUI();
		configureButtons();
		configureDatePickers();
	}
	
	//configure all the settings for the different UI components
	private void configureUI(){
		label.setValue("Select a Room or a Timeframe to make a reservation with dateformat");
		
		roomsCB.setFilteringMode(FilteringMode.CONTAINS);
		roomsCB.setInputPrompt("select a space");
		fillComboBox();
		
		selectHL.addComponents(roomsCB, selectBtn);
		selectHL.setMargin(true);
		
	}
	
	//configure handlers and settings for the buttons
	private void configureButtons(){
		selectBtn.addClickListener(e -> {
			String room = roomsCB.getValue().toString();
			navigator.navigateTo("reservationBySelectedRoom/"+room);
		});
		
		timeBtn.addClickListener(e -> {
			String start = startDate.getValue().toString();
			String end = endDate.getValue().toString();
			navigator.navigateTo("reservationBySelectedTime/"+start+"/"+end);
		});
		
		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
		
	}
	
	
	//fills the combo box with items
	private void fillComboBox(){
		for(String s: new String[]{"room1","room2","room3"}){
		roomsCB.addItem(s);
		}
	}
	
	//configure DateFields and add them to the dateLayout
	private void configureDatePickers(){
		Label dateFormat = new Label();
		dateHL.addComponents(startDate, endDate, timeBtn, dateFormat);
		dateHL.setMargin(true);
		
		startDate.setCaption("show rooms: FROM");
		startDate.setResolution(Resolution.MINUTE);
		startDate.setValue(new Date()); // Set the date and time to present
		

		endDate.setCaption("TO");
		endDate.setResolution(Resolution.MINUTE);
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date());
		gregCal.add(java.util.Calendar.HOUR, 1);
		endDate.setValue(gregCal.getTime()); // Set the date and time to present + 1h
		label.setValue("Select a Room or a Timeframe to make a reservation with dateformat"+startDate.getDateFormat());

	}
	
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		
		//load list of all rooms
	}

}
