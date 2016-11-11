package ch.bfh.blue.srs;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Reservation {
	private Set<Teilnehmer> participants = new HashSet<>();
	private Set<Space> spaces = new HashSet<>();
	private Space rentSpace;
	private Date todayDate;
	private Time duration;
	private Mieter mieter;
	private boolean perodic;
	
	public Reservation(Date todayDate, Time duration, boolean perodic){
		this.todayDate = todayDate;
		this.duration = duration;
		this.perodic = perodic;
	}
	
	public void addSpace(Space s1) {
		spaces.add(s1);
		s1.booking();
		
	}

	public Set<Space> getSpaces() {
		return Collections.unmodifiableSet(this.spaces);
	}

	public void setRenter(Mieter m) {
		this.mieter = m;
		
	}

	public Mieter getRenter() {
		return this.mieter;
	}

	public void addParticipants(Teilnehmer t1) {
		participants.add(t1);
		
	}

	public Set<Teilnehmer> getParticipants() {
		return Collections.unmodifiableSet(this.participants);
	}

}
