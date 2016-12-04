package ch.bfh.blue.UI;

import java.util.Date;

import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeListener;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeNotifier;

public class BasicEvent
	implements CalendarEvent, EventChangeNotifier{

	@Override
	public void addEventChangeListener(EventChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventChangeListener(EventChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStyleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAllDay() {
		// TODO Auto-generated method stub
		return false;
	}

}
