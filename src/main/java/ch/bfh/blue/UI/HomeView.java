/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

import ch.bfh.blue.service.Controller;

/**
 * This is the main page which is also set as default in the navigator
 *
 * @author SRS-Team
 *
 */

public class HomeView extends FormLayout implements View {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Navigator navigator;
	private Controller controller;

	// Labels and Components
	private final Label label = new Label();
	private final Label lLabel = new Label();
	private final Label rLabel = new Label();

	// Buttons
	private final Button loginBtn = new Button("Login here");
	private final Button registerBtn = new Button("Register here");
	private final Button initBtn = new Button("Take default Database");

	public HomeView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { label, lLabel, loginBtn, rLabel, registerBtn, initBtn })
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	// configure all the settings for the different UI components
	private void configureUI() {
		label.setValue("Welcome to SRS!");
		label.setStyleName("mainCaption");
		lLabel.setValue("To use the SRS, please login.");
		rLabel.setValue("New here?\n Register a new Account.");
		initBtn.setDisableOnClick(true);
	}

	// configure handlers and settings for the buttons
	private void configureButtons() {
		loginBtn.addClickListener(e -> {
			navigator.navigateTo("login");
		});

		registerBtn.addClickListener(e -> {
			navigator.navigateTo("register");
		});

		initBtn.addClickListener(e -> {
			if ((Boolean) getSession().getAttribute("databaseLock") == true) {
				createDemoPersons();
				createDemoRooms();
				getSession().setAttribute("databaseLock", false);
			}
		});
	}

	/**
	 * adds a few rooms to the db to test the functionality
	 */
	private void createDemoRooms() {
		controller.createSpace("aula", 1);
		controller.createSpace("garage", 2);
		controller.createSpace("tennisplatz", 3);
	}

	/**
	 * creates a few persons with username and pw to test the functionality
	 */
	private void createDemoPersons() {
		controller.createPerson("one@mail", "one", "1");
		controller.createPerson("two@mail", "two", "2");
		controller.createPerson("three@mail", "three", "3");
	}


	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
	}

}
