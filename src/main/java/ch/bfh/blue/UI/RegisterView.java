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
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import ch.bfh.blue.service.Controller;

/**
 * This view provides everything necessary to create
 * a new account with username and password
 * @author SRS-Team
 *
 */

public class RegisterView extends FormLayout implements View {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Navigator navigator;
	private Controller controller;

	//Constants
	private static final String REGISTRATION_FAIL_PW_MISSMATCH =
			"Your Password and your retyped Password don't match, try again.";
	private static final String REGISTRATION_SUCCESS =
			"Registration successful, please login.";

	//Layouts which contain components
	private final HorizontalLayout buttonBar = new HorizontalLayout();

	//Labels and Components
	private final Label header = new Label();
	private final TextField user = new TextField();
	private final TextField email = new TextField();
	private final PasswordField passwd = new PasswordField();
	private final PasswordField retypepw = new PasswordField();
	private Notification notif = new Notification("",Notification.Type.WARNING_MESSAGE);

	//Buttons
	private final Button registerBtn = new Button("Register");
	private final Button homeBtn = new Button("Home");

	public RegisterView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { header, email, user, passwd, retypepw, buttonBar })
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI() {
		header.setValue("REGISTER");
		user.setCaption("Username");
		user.setInputPrompt("Enter username");
		email.setCaption("E-mail");
		email.setInputPrompt("Enter e-mail");
		passwd.setCaption("Password");
		passwd.setInputPrompt("password");
		retypepw.setCaption("Confirm");
		retypepw.setInputPrompt("password");

		notif.setDelayMsec(3500);
	}

	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons(){
		buttonBar.addComponents(homeBtn, registerBtn);
		//buttonBar.setMargin(true);
		buttonBar.setSpacing(true);

		registerBtn.addClickListener(e -> {
				if(passwd.getValue().equals(retypepw.getValue())){
					controller.createPerson(email.getValue(), user.getValue(),passwd.getValue());
					navigator.navigateTo("home");
					Notification notif = new Notification(REGISTRATION_SUCCESS,Notification.Type.WARNING_MESSAGE);
					notif.setDelayMsec(3500);
					notif.show(Page.getCurrent());
				} else {
					notif.setCaption(REGISTRATION_FAIL_PW_MISSMATCH);
					notif.show(Page.getCurrent());
				}

		});

		homeBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
	}

	/**
	 * is called upon entering the view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
	}

}
