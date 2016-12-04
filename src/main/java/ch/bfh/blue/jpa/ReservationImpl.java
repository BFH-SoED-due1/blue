/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.sql.Timestamp;

import javax.persistence.Entity;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;



@Entity
public class ReservationImpl implements Reservation {
	private Space rentSpace;
	private Person owner;
	private Timestamp stStamp,enStamp;


	public ReservationImpl(Person p,Timestamp stStamp,Timestamp enStamp,Space space){
		this.owner = p;
		this.rentSpace = space;
		this.stStamp =stStamp;
		this.enStamp = enStamp;

	}

	@Override
	public void setSpace(Space s1) {
		rentSpace = s1;
		s1.booking();

	}

	@Override
	public Space getSpace() {
		return rentSpace;
	}

	@Override
	public void setOwner(Person p) {
		this.owner = p;

	}

	@Override
	public Person getOwner() {
		return this.owner;
	}

}
