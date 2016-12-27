package ch.bfh.blue.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.UIState.NotificationTypeConfiguration;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginView extends FormLayout implements View{
	
	Navigator navigator;
	
	private final Label header = new Label();
	private final TextField email = new TextField();
	private final PasswordField passwd = new PasswordField();
	private final HorizontalLayout buttonBar = new HorizontalLayout();
	
	private final Button loginBtn = new Button("Login");
	private final Button homeBtn = new Button("Home");
	
	public LoginView(){
		for(Component c: new Component[]{ header, email, passwd, buttonBar})
			this.addComponent(c);
		configureUI();
	}

	private void configureUI(){
		header.setValue("LOGIN");
		email.setCaption("E-mail");
		email.setInputPrompt("Enter e-mail");
		passwd.setCaption("Password");
		passwd.setInputPrompt("Enter password");
		
		buttonBar.addComponents(homeBtn, loginBtn);
		buttonBar.setMargin(true);
		buttonBar.setSpacing(true);
		
		loginBtn.addClickListener(e -> {
			//Database Queries
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
