package ch.bfh.blue.UI;

import java.util.Date;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.blue.requirements.Person;
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
	//Constants
		private static final String RESERVATION_SUCCESS =
				"You have successfully made a reservation.";

	// Layouts which contain components
	private final HorizontalLayout navBtnHL = new HorizontalLayout();
	private final HorizontalLayout resHL = new HorizontalLayout();

	//Labels and Components
	String dates = new String();
	private Date startDate;
	private Date endDate;
	private final Label heading = new Label();
	private final Grid grid = new Grid();
	private final TextField title = new TextField();
	private Notification notif = new Notification("",Notification.Type.WARNING_MESSAGE);
	private Boolean firstEnter = true;


	//Buttons
	private final Button reservationBtn = new Button("Make reservation");
	private final Button logoutBtn = new Button("Logout");
	private final Button backBtn = new Button("Back");

	public ReservationBySelectedTimeView(Controller contr){
		controller = contr;
		for (Component c : new Component[] {heading, grid, resHL, navBtnHL})
			this.addComponent(c);
		notif.setDelayMsec(3500);
		configureUI();
		configureButtons();
	}

	/**
	 * configure all the settings for the different UI components here
	 */
	private void configureUI(){
		this.setSpacing(true);
		heading.setValue("Available rooms for the selected timeframe:");
		title.setInputPrompt("Enter title");
		resHL.addComponents(title, reservationBtn);
		resHL.setSpacing(true);
		configureGrid();
	}

	private void configureGrid(){
		grid.setWidth("300px");
		grid.setHeight("400px");
	}

	/**
	 * configure handlers and settings for the buttons here
	 */
	private void configureButtons(){
		reservationBtn.addClickListener(e->{
			Person p = (Person)getSession().getAttribute("user");
			Space spc = (Space)grid.getSelectedRow();
			controller.createReservation(title.getValue(), p, startDate, endDate, spc);
			notif.setCaption(RESERVATION_SUCCESS+"("+title.getValue()+", "+spc+", from"+startDate+" to "+endDate);
			notif.show(Page.getCurrent());
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
		startDate = (Date)getSession().getAttribute("startDate");
		endDate = (Date)getSession().getAttribute("endDate");
		List<Space> spaces = controller.getSpaceOnTime(startDate, endDate);
		grid.setContainerDataSource(new BeanItemContainer<>(Space.class, spaces));
		if(!spaces.isEmpty()&&firstEnter){
			firstEnter = false;
		grid.removeColumn("reservations");
		}
		navigator = event.getNavigator();
	}

}
