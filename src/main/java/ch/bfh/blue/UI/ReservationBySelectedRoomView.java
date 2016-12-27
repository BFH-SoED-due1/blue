package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class ReservationBySelectedRoomView extends CssLayout implements View {
	
	private Navigator navigator;
	
	//Labels and Components

	private final Label label = new Label();
	private final Label enter = new Label();
	
	//Buttons
	private final Button homeBtn = new Button("Home");
	
	public ReservationBySelectedRoomView(){
		for (Component c : new Component[] {label, homeBtn, enter})
			this.addComponent(c);
		configureUI();
		configureButtons();
	}
	
	//configure all the settings for the different UI components
	private void configureUI(){
		label.setValue("available timeframes for the selected room:");
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

		//list times for the selected room
		//list rooms for the selected time
		
	}

}
