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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

@Entity
public class ReservationImpl implements Reservation {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(targetEntity = SpaceImpl.class)
	private Space rentSpace;

	@ManyToOne(targetEntity = Mieter.class)
	private Person owner;

	private Timestamp stStamp, enStamp;

	private String title;

	public ReservationImpl() {

	}

	public ReservationImpl(String title, Person p, Timestamp stStamp, Timestamp enStamp, Space space) {
		this.setTitle(title);
		this.owner = p;
		this.rentSpace = space;
		this.stStamp = stStamp;
		this.enStamp = enStamp;

	}

	@Override
	public void setSpace(Space s1) {
		rentSpace = s1;

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

	@Override
	public Timestamp getStStamp() {
		return stStamp;
	}

	@Override
	public void setStStamp(Timestamp stStamp) {
		this.stStamp = stStamp;
	}

	@Override
	public Timestamp getEnStamp() {
		return enStamp;
	}

	@Override
	public void setEnStamp(Timestamp enStamp) {
		this.enStamp = enStamp;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
