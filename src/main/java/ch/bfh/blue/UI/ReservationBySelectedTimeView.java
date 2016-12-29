package ch.bfh.blue.UI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

import ch.bfh.blue.service.Controller;

/**
 * After selecting a timeframe, this view will show all the
 * available rooms for this timeframe
 * @author SRS-Team
 *
 */

public class ReservationBySelectedTimeView extends CssLayout implements View {
	
	private Navigator navigator;
	Controller controller;
	
	//Constants
	private static final String DATE_FORMAT = "dd.MM.yy kk:mm";
	
	//Labels and Components
	String dates = new String();
	private Date startDate;
	private Date endDate;
	private final Label label = new Label();
	private final Label enter = new Label();
	
	//Buttons
	private final Button homeBtn = new Button("Home");
	
	public ReservationBySelectedTimeView(Controller contr){
		controller = contr;
		for (Component c : new Component[] {label, homeBtn, enter})
			this.addComponent(c);
		configureUI();
		configureButtons();
	}
	
	//configure all the settings for the different UI components
	private void configureUI(){
		label.setValue("available rooms for the selected timeframe");
	}
	
	//configure handlers and settings for the buttons
	private void configureButtons(){
		homeBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		dates = event.getParameters();
		String twoDates[] = dates.split("/");
		DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			startDate = format.parse(twoDates[0]);
			endDate = format.parse(twoDates[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		enter.setCaption(startDate+"||"+endDate);
	}

}
