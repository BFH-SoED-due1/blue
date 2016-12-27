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

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		final HorizontalLayout datePickerLayout = new HorizontalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		DateField startDate = new DateField("Start of Reservation");
		startDate.setResolution(Resolution.MINUTE);
		startDate.setValue(new Date()); // Set the date and time to present

		DateField endDate = new DateField("End of Reservation");
		endDate.setResolution(Resolution.MINUTE);
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date());
		gregCal.add(java.util.Calendar.HOUR, 1);
		endDate.setValue(gregCal.getTime()); // Set the date and time to present + 1h

		MyEventProvider evProv = new MyEventProvider();

		Button reservationBtn = new Button("Make Reservation");
		reservationBtn.addClickListener(e -> {
			
			layout.addComponent(new Label(
					"Thanks " + startDate.getValue() + ", it works!" + endDate.getValue()
					));
						
		});

		Calendar cal = new Calendar(evProv);

		datePickerLayout.addComponents(startDate, endDate);
		datePickerLayout.setSpacing(true);
		layout.addComponents(name, datePickerLayout, reservationBtn, cal);
		layout.setMargin(true);
		layout.setSpacing(true);

		
		
		setContent(new LoginWindow().createLogin());
	}

	// testkommentar

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}