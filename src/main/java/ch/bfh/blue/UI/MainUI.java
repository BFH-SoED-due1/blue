/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.UI;


import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

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
public class MainUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		final CssLayout topBar = new CssLayout();
		final CssLayout viewLayout = new CssLayout();
		

		
		final Navigator navigator = new Navigator(this, viewLayout);
		navigator.addView("", HomeView.class);
		navigator.addView("home", new HomeView());
		navigator.addView("login", LoginView.class);
		navigator.addView("register", RegisterView.class);
		
		//Button loginBtn = new Button("Login");
		//Button registerBtn = new Button("Register");
		//topBar.addComponents(loginBtn,registerBtn);
		
//		loginBtn.addClickListener(e -> {
//			navigator.navigateTo("login");
//						
//		});
//		
//		registerBtn.addClickListener(e -> {
//			navigator.navigateTo("register");
//						
//		});
		
		for(String s: new String[]{"home"}){
			topBar.addComponent(this.createNavigationButton(s, navigator));
		}
		
		layout.addComponent(viewLayout);
		layout.setMargin(true);
		setContent(layout);
		
	}
	
	private Button createNavigationButton(String state, Navigator navigator){
		return new Button(state, new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(state);
			}
		});
	}
	

	

	// testkommentar
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}


}
