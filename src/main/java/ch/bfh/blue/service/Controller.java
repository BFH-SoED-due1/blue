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
	
	private static Controller instance;
	
	private AbstractPersistencManager pm;
	private Person currentPerson = null;
	private Space currentSpace = null;
	private Reservation currentReservation = null;
	private Date[] currentDate = null;
	

	public Controller() throws InstantiationException, IllegalAccessException {
		pm = AbstractPersistencManager.getInstance();
		
		createSpace("aula", 1);
		createSpace("garage", 2);
		createSpace("tennisplatz", 3);
		
		createPerson("one@mail", "one", "1");
		createPerson("two@mail", "two", "2");
		createPerson("three@mail", "three", "3");
	}
	
	public static Controller getInstance() throws InstantiationException, IllegalAccessException{
		  if (instance == null){
		   instance = new Controller();
		  }
		  return instance;
		 }

	public Person authentication(String user, String pw) {
		return pm.makeLoginQuery(user, pw);
	}

	public List<Space> getSpaceOnTime(Date stDate, Date enDate) {
		return pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));
	}

	public boolean isFree(Date stDate, Date enDate, Space s) {
		List<Space> spaces = pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));
		return spaces.contains(s);
	}

	public void createReservation(String title, Person p, Date stDate, Date enDate, Space space) {
		pm.makeReservation(title, p, new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()), space);
	}

	public void createSpace(String name, int spaceNumber) {
		pm.makeSpace(name, spaceNumber);
	}

	public void createPerson(String email, String user, String pw) {
		UserData data = (UserData) new UserDataImpl(email, user, pw);
		pm.makePerson(data);
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
