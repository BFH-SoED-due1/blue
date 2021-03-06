/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

@Entity
@NamedQuery(name="SpaceImpl.findAll", query="SELECT s FROM SpaceImpl s")
public class SpaceImpl implements Space{

	@Id
	private int spaceNumber;
	private String name;


	@OneToMany(targetEntity=ReservationImpl.class, mappedBy="rentSpace")
	private List<Reservation> spaceReservations= new ArrayList<>();

	public SpaceImpl() {

	}

	public SpaceImpl(String name,int spaceNumber){
		this.name=name;
		this.spaceNumber = spaceNumber;
	}


	@Override
	public String getName() {
		return this.name;
	}



	@Override
	public List<Reservation> getReservations() {
		return Collections.unmodifiableList(this.spaceReservations);
	}



	@Override
	public void addReservation(Reservation res) {
		this.spaceReservations.add(res);

	}


	@Override
	public void removeReservation(Reservation res) {
		if (this.spaceReservations.contains(res)){
			this.spaceReservations.remove(res);
		}

	}


	@Override
	public String toString(){
		return name;
	}

	@Override
	public int getSpaceNumber() {
		return spaceNumber;
	}

	@Override
	public void setSpaceNumber(int spaceNumber) {
		this.spaceNumber = spaceNumber;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setReservations(List<Reservation> reservations) {
		this.spaceReservations = reservations;
	}

}
