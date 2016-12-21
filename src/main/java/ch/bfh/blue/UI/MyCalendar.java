package ch.bfh.blue.UI;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.ui.Calendar;
import com.vaadin.ui.components.calendar.event.CalendarEditableEventProvider;

public class MyCalendar {
	
	CalendarEditableEventProvider evProv;
	
	public MyCalendar(CalendarEditableEventProvider evProv){
		this.evProv = evProv;
		Calendar cal = new Calendar(evProv);
	}
	
	
	


}
