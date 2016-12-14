/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.service;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.jpa.UserData;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

public class PersistancManger extends AbstractPersistencManager {
	private EntityManager em;

	public PersistancManger() {
		init();
	}

	public void init(){
		EntityManagerFactory factory =
		Persistence.createEntityManagerFactory("srs-pu");
		this.em = factory.createEntityManager();
	}

	public void close(){
		this.em.close();
	}

	public void deletePerson(Person p){
		em.remove(p);
	}

	@Override
	public Person makePerson(UserData data) {
		em.getTransaction().begin();
		Person p = new Mieter(data);
		em.persist(p);
		em.getTransaction().commit();
		return p;
	}

	@Override
	public Space makeSpace(String name, int spaceNumber) {
		em.getTransaction().begin();
		Space p = new SpaceImpl(name, spaceNumber);
		em.persist(p);
		em.getTransaction().commit();
		return p;
	}

	@Override
	public Reservation makeReservation(Person p,Timestamp stStamp,Timestamp enStamp,Space space) {
		em.getTransaction().begin();
		Reservation r = new ReservationImpl(p, stStamp,enStamp,space);
		em.persist(r);
		em.getTransaction().commit();
		return r;

	}





}
