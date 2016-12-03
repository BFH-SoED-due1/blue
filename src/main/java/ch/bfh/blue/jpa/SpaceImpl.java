/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

@Entity
public class SpaceImpl implements Space{

	@Id
	private int spaceNumber;
	private String name;
	private boolean booked = false;

	@OneToMany
	private Set<Reservation> reservations = new HashSet<>();

	public SpaceImpl(String name,int spaceNumber){
		this.name=name;
		this.spaceNumber = spaceNumber;
	}

	// returns if room is booked
	@Override
	public boolean isBooked() {
		return this.booked;
	}

	@Override
	public void booking() {
		this.booked = true;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(this.reservations);
	}

	@Override
	public void addReservation(Reservation res) {
		this.reservations.add(res);

	}

	@Override
	public void removeReservation(Reservation res) {
		if (this.reservations.contains(res)){
			this.reservations.remove(res);
		}

	}
}
