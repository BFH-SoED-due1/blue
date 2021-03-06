/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.UI;

import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.blue.service.Controller;

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


	private Controller controller;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		final CssLayout viewLayout = new CssLayout();
		final Navigator navigator;

		getSession().setAttribute("databaseLock", true);
		try {
			controller = new Controller();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		getSession().setAttribute("user", null);

		// /*
		// * is called upon closing of the UI do cleanup stuff here
		// */
		// addDetachListener(new DetachListener() {
		// @Override
		// public void detach(DetachEvent event) {
		// // during work
		// controller.close();
		//
		// }
		// });

		navigator = new Navigator(this, viewLayout);
		navigator.addView("", new HomeView(controller));
		navigator.addView("home", new HomeView(controller));
		navigator.addView("login", new LoginView(controller));
		navigator.addView("register", new RegisterView(controller));
		navigator.addView("availableSpaces", new AvailableSpacesView(controller));
		navigator.addView("reservationBySelectedRoom", new ReservationBySelectedRoomView(controller));
		navigator.addView("reservationBySelectedTime", new ReservationBySelectedTimeView(controller));

		layout.addComponent(viewLayout);
		layout.setMargin(true);
		setContent(layout);
	}

	// testkommentar
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	@PersistenceContext(name = "persistence/em", unitName = "srs-pu")
	public static class MyUIServlet extends VaadinServlet {
	}

}
