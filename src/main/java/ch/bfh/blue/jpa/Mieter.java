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
import ch.bfh.blue.requirements.UserData;

@Entity
@NamedQuery(name="Mieter.findAll", query="SELECT m FROM Mieter m")
public class Mieter implements Person {

	@Id @GeneratedValue
	private int m_id;

	@Embedded
	private UserDataImpl data;

	@OneToMany(targetEntity=ReservationImpl.class, mappedBy="owner")
	private List<Reservation> personReservations = new ArrayList<>();


	@Override
	public int getId() {
		return m_id;
	}

	@Override
	public void setId(int id) {
		this.m_id = id;
	}


	@Override
	public void setData(UserData data2) {
		this.data = (UserDataImpl) data2;
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

	@Override
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

	@Override
	public String toString(){
		return this.getData().getUserName();
	}


}
