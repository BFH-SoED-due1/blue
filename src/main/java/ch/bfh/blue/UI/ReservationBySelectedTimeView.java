package ch.bfh.blue.UI;

import java.util.Date;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

/**
 * After selecting a timeframe, this view will show all the
 * available rooms for this timeframe
 * @author SRS-Team
 *
 */

public class ReservationBySelectedTimeView extends VerticalLayout implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Navigator navigator;
	Controller controller;
	
	//Constants
	private static final String DATE_FORMAT = "dd.MM.yy kk:mm";
	
	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	
	//Labels and Components
	String dates = new String();
	private Date startDate;
	private Date endDate;
	private final Label heading = new Label();
	private final Grid grid = new Grid();
	
	//Buttons
	private final Button reservationBtn = new Button("Make reservation");
	private final Button logoutBtn = new Button("Logout");
	private final Button backBtn = new Button("Back");
	
	public ReservationBySelectedTimeView(Controller contr){
		controller = contr;
		for (Component c : new Component[] {heading, grid, reservationBtn, navBtnHL})
			this.addComponent(c);
		configureUI();
		configureButtons();
	}
	
	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI(){
		this.setSpacing(true);
		heading.setValue("Available rooms for the selected timeframe:");
		configureGrid();
	}
	
	private void configureGrid(){
		grid.setWidth("800px");
		grid.setHeight("600px");
		//grid.setContainerDataSource(new BeanItemContainer<>(Space.class, controller.getSpaceOnTime(startDate, endDate)));
	}
	
	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons(){
		
		reservationBtn.addClickListener(e->{
			
		});
		
		logoutBtn.addClickListener(e -> {
			navigator.navigateTo("home");
		});
		
		backBtn.addClickListener(e -> {
			navigator.navigateTo("availableSpaces");
		});
		
		navBtnHL.addComponents(backBtn, logoutBtn);
		navBtnHL.setSpacing(true);
	}

	/**
	 * called upon entering the view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		//Date[] dates = controller.getCurrentDate();
		//startDate = dates[0];
		//endDate = dates[1];
		navigator = event.getNavigator();
	}

}
