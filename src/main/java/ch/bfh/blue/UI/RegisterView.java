package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegisterView extends FormLayout implements View {

	private Navigator navigator;
	
	//Layouts which contain components
	private final HorizontalLayout buttonBar = new HorizontalLayout();

	//Labels and Components
	private final Label header = new Label();
	private final TextField email = new TextField();
	private final TextField remail = new TextField();
	private final PasswordField passwd = new PasswordField();
	private final PasswordField retypepw = new PasswordField();

	//Buttons
	private final Button registerBtn = new Button("Register");
	private final Button homeBtn = new Button("Home");

	public RegisterView() {
		for (Component c : new Component[] { header, email, remail, passwd, retypepw, buttonBar })
			this.addComponent(c);
		configureUI();
		configureButtons();
	}

	//configure all the settings for the different UI components
	private void configureUI() {
		header.setValue("REGISTER");
		email.setCaption("E-mail");
		email.setInputPrompt("Enter e-mail");
		remail.setCaption("Confirm");
		remail.setInputPrompt("Confirm e-mail");
		passwd.setCaption("Password");
		passwd.setInputPrompt("enter password");
		retypepw.setCaption("Confirm");
		retypepw.setInputPrompt("confirm password");	
	}
	
	//configure handlers and settings for the buttons
	private void configureButtons(){
		buttonBar.addComponents(homeBtn, registerBtn);
		buttonBar.setMargin(true);
		buttonBar.setSpacing(true);
		
		registerBtn.addClickListener(e -> {
			// Database Queries
			navigator.navigateTo("availableSpaces");
		});

		homeBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
	}

}
