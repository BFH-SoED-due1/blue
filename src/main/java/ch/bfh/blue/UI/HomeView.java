package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import ch.bfh.blue.service.Controller;

/**
 * This is the main page which is also set as default in the navigator
 * @author SRS-Team
 *
 */

public class HomeView extends FormLayout implements View {

	private Navigator navigator;
	private Controller controller;

	//Constants
	private static final String REGISTER_SUCCESS = "Registration successful, please login.";

	//Labels and Components
	private final Label label = new Label();
	private final Label lLabel = new Label();
	private final Label rLabel = new Label();

	//Buttons
	private final Button loginBtn = new Button("Login here");
	private final Button registerBtn = new Button("Register here");
	private final Button initBtn = new Button("Take default Database");

	public HomeView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { label, lLabel, loginBtn, rLabel, registerBtn,initBtn})
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	//configure all the settings for the different UI components
	private void configureUI() {
		label.setValue("Welcome to SRS!");
		label.setStyleName("mainCaption");
		lLabel.setValue("To use the SRS, please login.");
		rLabel.setValue("New here?\n Register a new Account.");
		initBtn.setDisableOnClick(true);
	}

	//configure handlers and settings for the buttons
	private void configureButtons(){
		loginBtn.addClickListener(e -> {
			navigator.navigateTo("login");
		});

		registerBtn.addClickListener(e -> {
			navigator.navigateTo("register");
		});

		initBtn.addClickListener(e -> {
			createDemoPersons();
			createDemoRooms();
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		String regOk = "regOk";
		if(event.getParameters().equals(regOk)){
			Notification notif = new Notification(REGISTER_SUCCESS,Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(3500);
			notif.show(Page.getCurrent());
		}
	}
	/**
	 * adds a few rooms to the db to test the functionality
	 */
	private void createDemoRooms(){
		controller.createSpace("aula", 1);
		controller.createSpace("garage", 2);
		controller.createSpace("tennisplatz", 3);
	}

	/**
	 * creates a few persons with username and pw to test the functionality
	 */
	private void createDemoPersons(){
		controller.createPerson("one@mail", "one", "1");
		controller.createPerson("two@mail", "two", "2");
		controller.createPerson("three@mail", "three", "3");
	}

	/**
	 * creates a few reservations to test the functionality
	 */
	private void createDemoReservations(){

	}

}
