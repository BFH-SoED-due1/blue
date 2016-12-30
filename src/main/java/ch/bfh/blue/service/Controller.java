package ch.bfh.blue.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.requirements.UserData;

public class Controller {
	private AbstractPersistencManager pm;

	private Person currentPerson = null;
	private Space currentSpace = null;
	private Reservation currentReservation = null;
	private Date[] currentDate = null;


	public Controller() throws InstantiationException, IllegalAccessException {
		pm = AbstractPersistencManager.getInstance();
	}

	public Person authentication(String user, String pw) {
		return pm.makeLoginQuery(user, pw);
	}

	public List<Space> getSpaceOnTime(Date stDate, Date enDate) {
		Timestamp startStamp = new Timestamp(stDate.getTime());
		Timestamp endStamp = new Timestamp(enDate.getTime());
		return pm.getFreeSpaces(startStamp, endStamp);
	}

	public boolean isFree(Date stDate, Date enDate, Space s) {
		List<Space> spaces = pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));
		return spaces.contains(s);
	}

	public Reservation createReservation(String titles,Person p,Date stDate, Date enDate,Space space){
		return pm.makeReservation(titles, p, new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()), space);
	}

	public Space createSpace(String name,int spaceNumber){
		return pm.makeSpace(name, spaceNumber);
	}

	public Person createPerson(String email,String user,String pw){
		UserData data = new UserDataImpl(email,user,pw);
		return pm.makePerson(data);
	}

	public List<Space> getAllspaces() {
		return pm.getAllSpaces();
	}

//********** setters and getters for current values **********
	public Person getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(Person currentPerson) {
		this.currentPerson = currentPerson;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}

	public Reservation getCurrentReservation() {
		return currentReservation;
	}

	public void setCurrentReservation(Reservation currentReservation) {
		this.currentReservation = currentReservation;
	}

	public Date[] getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date startDate, Date endDate) {
		this.currentDate = new Date[]{startDate, endDate};
	}

	/**
	 * calls the close function of the persistence manager
	 */
	public void close() {
		pm.close();
	}

}
