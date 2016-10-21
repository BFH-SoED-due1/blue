package ch.bfh.blue.srs;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Reservation {
	private List<Teilnehmer> participants = new LinkedList<>();
	private Space rentSpace;
	private Date todayDate;
	private Time duration;
	private boolean perodic;
	
	public Reservation(Date todayDate, Time duration, boolean perodic){
		this.todayDate = todayDate;
		this.duration = duration;
		this.perodic = perodic;
	}
	
	public void addSpace(Space s1) {
		// TODO Auto-generated method stub
		
	}

	public Object getSpace() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRenter(Mieter m) {
		// TODO Auto-generated method stub
		
	}

	public Object getRenter() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addParticipants(Person t1) {
		// TODO Auto-generated method stub
		
	}

	public Object getParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

}
