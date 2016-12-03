/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;

@Entity
public class Mieter implements Person {

	@Id @GeneratedValue
	private int id;

	@Embedded
	private UserData data;

	@OneToMany
	private Set<Reservation> reservations = new HashSet<>();

	public Mieter(UserData data) {
		this.data=data;
	}

	// get the set of reservations
	public UserData getData() {
		return this.data;
	}

	@Override
	public Set<Reservation> getReservations() {
		return reservations;
	}


	@Override
	public void addReservation(Reservation r) {
		reservations.add(r);

	}

	@Override
	public void removeReservation(Reservation r) {
		if (reservations.contains(r)) {
			reservations.remove(r);
		}
	}

	@Override
	public void setReservation(Set<Reservation> reservations){
		this.reservations = reservations;
	}

}
