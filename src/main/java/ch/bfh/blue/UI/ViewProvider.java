package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

public class ViewProvider {
	
	UI ui;
	
	public ViewProvider(UI ui){
		this.ui = ui;
	}

	//creates a view to login
	public Layout createLoginView(){
		VerticalLayout layout = new VerticalLayout();
		
		Label loginLabel = new Label("Please login:");
		Button loginBtn = new Button("Login");
		TextField username = new TextField("Username");
		PasswordField pw = new PasswordField("Password");
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponents(loginLabel, username,pw,loginBtn);
		return layout;
	}
	
	//creates a view for registering a new user
	public Layout createRegistrationView(){
		VerticalLayout layout = new VerticalLayout();
		
		Label registerLabel = new Label("Create New Account:");
		TextField registername = new TextField("Choose a Username");
		PasswordField newPw = new PasswordField("Password");
		PasswordField newPwAgain = new PasswordField("Confirm Password");
		Button registerBtn = new Button("Register new User");
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponents(registerLabel, registername, newPw, newPwAgain, registerBtn);
		return layout;
	}
	
	public Layout createReservationView(){
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout datePickerLayout = new HorizontalLayout();
		
		final TextField name = new TextField();
		name.setCaption("Type your name here:");
		
		DateField startDate = createDateField("Start of Reservation", new Date(), Resolution.MINUTE);
		DateField endDate = createDateField("End of Reservation", new Date(), Resolution.MINUTE);
		addHour(endDate);

		
		BasicEventProvider evProv = new BasicEventProvider();
		Calendar cal = new Calendar();
		
		
		Label confirmationMessage = new Label();
		Button reservationBtn = new Button("Make Reservation");
		reservationBtn.addClickListener(e -> {
			cal.addEvent(createEvent(startDate.getValue(), endDate.getValue(), name.getValue(), name.getValue()));
			confirmationMessage.setValue(
					"Made Reservation from: " + startDate.getValue() + ", to: " + endDate.getValue()
					);
						
		});
		
		
		datePickerLayout.addComponents(startDate, endDate);
		datePickerLayout.setSpacing(true);
		layout.addComponents(name, datePickerLayout, reservationBtn, cal, confirmationMessage);
		layout.setMargin(true);
		layout.setSpacing(true);
		
		return layout;
	}
	
	
	public CalendarEvent createEvent(Date start, Date end, String title, String description){
		BasicEvent event = new BasicEvent(description, title, start, end);
		return event;
	}
	
//**************************** helper methods ****************************
	private DateField createDateField(String caption, Date date, Resolution res){
		DateField dateField = new DateField(caption);
		dateField.setResolution(res);
		dateField.setValue(date);

		return dateField;
	}
	
	private void addHour(DateField date){
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(date.getValue());
		gregCal.add(GregorianCalendar.HOUR, 1);
		date.setValue(gregCal.getTime());
	}
	
}
