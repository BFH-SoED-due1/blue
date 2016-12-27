/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;

@Entity
@NamedQuery(name="Mieter.findAll", query="SELECT m FROM Mieter m")
public class Mieter implements Person {

	@Id @GeneratedValue
	private int id;

	@Embedded
	private UserDataImpl data;

	@OneToMany(targetEntity=ReservationImpl.class)
	private List<Reservation> personReservations = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setData(UserDataImpl data) {
		this.data = data;
	}

	@Override
	public void setReservation(List<Reservation> reservations) {
		this.personReservations = reservations;
	}



	public Mieter() {

	}

	public Mieter(UserDataImpl data2) {
		this.data= data2;
	}

	// get the set of reservations
	public UserDataImpl getData() {
		return this.data;
	}

	@Override
	public List<Reservation> getReservations() {
		return personReservations;
	}


	@Override
	public void addReservation(Reservation r) {
		personReservations.add(r);

	}

	@Override
	public void removeReservation(Reservation r) {
		if (personReservations.contains(r)) {
			personReservations.remove(r);
		}
	}



}
