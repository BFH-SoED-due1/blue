/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.srs;

public class Space {
	private String name;
	private boolean booked = false;

	public Space(String name){
		this.name=name;
	}

	// returns if room is booked
	public boolean isBooked() {
		return this.booked;
	}

	public void booking() {
		this.booked = true;
	}
}
