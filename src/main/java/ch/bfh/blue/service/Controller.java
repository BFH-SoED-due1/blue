package ch.bfh.blue.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Space;

public class Controller {
	private AbstractPersistencManager pm;

	public Controller() throws InstantiationException, IllegalAccessException {
		 pm = AbstractPersistencManager.getInstance();

	}

	public boolean authentication(String user,String pw){
		return pm.makeLoginQuery(user, pw) != null;
	}

	public List<Space> getSpaceOnTime(Date stDate, Date enDate){
		return pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));

	}

	public boolean isFree(Date stDate, Date enDate,Space s){
		List<Space> spaces = pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));
		return spaces.contains(s);
	}

	public void createReservation(Person p,Date stDate, Date enDate,Space space){
		pm.makeReservation(p, new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()), space);
	}

	public void createSpace(String name,int spaceNumber){
		pm.makeSpace(name, spaceNumber);
	}

	public void createPerson(String email,String user,String pw){
		UserDataImpl data = new UserDataImpl(email,user,pw);
		pm.makePerson(data);
	}


}
