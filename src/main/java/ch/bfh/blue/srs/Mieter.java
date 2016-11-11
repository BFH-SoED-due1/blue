/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.blue.srs;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Mieter extends Person {

	private Set<Reservation> reservations = new HashSet<>();


	public Mieter(String name, String addresse, Date birthday) {
		super(name, addresse, birthday);
		// TODO Auto-generated constructor stub
	}
	// get the set of reservations
	public Set<Reservation> getReservations (){
		return reservations;
	}

	public void addReservation(Reservation r) {
		reservations.add(r);

	}


}
