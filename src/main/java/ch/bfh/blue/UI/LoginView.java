package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import ch.bfh.blue.service.Controller;

/**
 * This view provides everything necessary to login with username and password
 * @author SRS-Team
 *
 */

public class LoginView extends FormLayout implements View  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Navigator navigator;
	private Controller controller;
	
	//Constants
	private static final String LOGIN_ERR_INVALID_DATA = "Username or Password are incorrect.";
	
	//Layouts which contain components
	private final HorizontalLayout buttonBar = new HorizontalLayout();

	//Labels and Components
	private final Label header = new Label();
	private final TextField user = new TextField();
	private final PasswordField passwd = new PasswordField();
	private final Label message = new Label();

	//Buttons
	private final Button loginBtn = new Button("Login");
	private final Button homeBtn = new Button("Home");

	public LoginView(Controller contr) {
		controller = contr;
		for (Component c : new Component[] { header, user, passwd, buttonBar, message })
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI() {
		header.setValue("LOGIN");
		user.setCaption("Username");
		user.setInputPrompt("Enter username");
		passwd.setCaption("Password");
		passwd.setInputPrompt("Enter password");

		buttonBar.addComponents(homeBtn, loginBtn);
		buttonBar.setMargin(true);
		buttonBar.setSpacing(true);	
	}
	
	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons(){
		loginBtn.addClickListener(e -> {
			String username, password;
			username = user.getValue();
			password = passwd.getValue();
			if(controller.authentication(username, password)){
				navigator.navigateTo("availableSpaces");
			} else {
				message.setValue(LOGIN_ERR_INVALID_DATA);
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
		user.clear();
		passwd.clear();
		message.setValue("");
		navigator = event.getNavigator();
	}

}
