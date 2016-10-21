package ch.bfh.blue.srs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Person {
	private List<Reservation> reservations = new LinkedList<>();
	private String name, addresse;
	private Date birthday;
	
	public Person(String name, String addresse, Date birthday){
		this.name =name;
		this.addresse=addresse;
		this.birthday=birthday;
	}
	

	public void addReservation(Reservation r) {
		// TODO Auto-generated method stub
		
	}

	public Object getReservation() {
		// TODO Auto-generated method stub
		return null;
	}
}
