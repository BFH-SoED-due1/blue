package ch.bfh.blue.UI;

import java.text.SimpleDateFormat;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class ReservationBySelectedTimeView extends CssLayout implements View {
	
	private Navigator navigator;
	
	//Labels and Components
	String dates = new String();
	private final Label label = new Label();
	private final Label enter = new Label();
	
	//Buttons
	private final Button homeBtn = new Button("Home");
	
	public ReservationBySelectedTimeView(){
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
		enter.setCaption(twoDates[0]+"||"+twoDates[1]);
		//DateFormat format = new SimpleDateFormat();
		//Date date = format
		//list times for the selected room
		//list rooms for the selected time
		
	}

}
