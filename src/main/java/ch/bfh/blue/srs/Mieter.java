/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.srs;


import java.util.HashSet;
import java.util.Set;

public class Mieter extends Person {

	private Set<Reservation> reservations = new HashSet<>();


	public Mieter(UserData data) {
		super(data);
	}
	// get the set of reservations
	public Set<Reservation> getReservations (){
		return reservations;
	}

	public void addReservation(Reservation r) {
		reservations.add(r);

	}


}
