package ch.bfh.blue.UI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;

public class MyEventProvider implements CalendarEventProvider {

	private List<CalendarEvent> events = new ArrayList<CalendarEvent>();

	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {

		BasicEvent event = new BasicEvent();
		event.setCaption("Jek default event 1h");
		event.setDescription("My Event Description");
		event.setStart(startDate);
		event.setEnd(endDate);
		events.add(event);

		return events;
	}

}
