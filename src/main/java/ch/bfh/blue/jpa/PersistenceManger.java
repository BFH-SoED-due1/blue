/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.requirements.UserData;

/**
 * This class manage the persistence of our classes
 * @author SRS-Team
 *
 */
public class PersistenceManger extends AbstractPersistencManager {
	private EntityManager em;
	private EntityManagerFactory factory;


	public PersistenceManger() {
		init();
	}

	public void init() {
		this.factory = Persistence.createEntityManagerFactory("srs-pu");
		this.em = factory.createEntityManager();
	}

	@Override
	public void close() {
		this.em.close();
		this.factory.close();
	}

	public void deletePerson(Person p) {
		em.remove(p);
	}

	@Override
	public Person makePerson(UserData data) {
		em.getTransaction().begin();
		Person p = new Mieter((UserDataImpl) data);
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
	public Reservation makeReservation(Person p, Timestamp stStamp, Timestamp enStamp, Space space) {
		em.getTransaction().begin();
		Reservation r = new ReservationImpl(p, stStamp, enStamp, space);
		em.persist(r);
		p.addReservation(r);
		space.addReservation(r);
		em.getTransaction().commit();
		return r;

	}

	@Override
	public List<Person> getAllPersons() {
		TypedQuery<Person> query = em.createNamedQuery("Mieter.findAll", Person.class);
		return query.getResultList();
	}

	@Override
	public List<Space> getAllSpaces() {
		TypedQuery<Space> query = em.createNamedQuery("SpaceImpl.findAll", Space.class);
		return query.getResultList();
	}

	@Override
	public Person makeLoginQuery(String name, String pw) {
		TypedQuery<Person> query = em.createQuery("SELECT m FROM Mieter m  WHERE m.data.userName = ?1 AND m.data.pw=?2",
				Person.class);
		query.setParameter(1, name);
		return query.setParameter(2, pw).getSingleResult();

	}

	@Override
	public List<Space> getFreeSpaces(Timestamp stStamp,Timestamp endStamp){
		TypedQuery<Space> query = em.createQuery("SELECT s From SpaceImpl s EXCEPT"
				+" SELECT r.rentSpace FROM ReservationImpl r WHERE r.stStamp>=?1 AND r.enStamp<=?2 EXCEPT"
				+" SELECT r.rentSpace FROM ReservationImpl r WHERE ?1 BETWEEN r.stStamp AND r.enStamp OR ?2 BETWEEN r.stStamp AND r.enStamp",
				Space.class);
		query.setParameter(1, stStamp);
		return query.setParameter(2, endStamp).getResultList();
	}


}
