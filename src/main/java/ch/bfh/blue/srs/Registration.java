/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.srs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.bfh.blue.requirements.Person;

public class Registration {
	private Map<String, Person> registeredUsers = new HashMap<>();

	public void register(UserData data, boolean isRenter) {
		if (isRenter) {
			registeredUsers.put(data.getUserName(), new Mieter(data));
		} else {
			registeredUsers.put(data.getUserName(), new Teilnehmer(data));
		}
	}

	public boolean login(String userName, String password, Person p) {
		return authentication(userName, password, p);
	}

	public boolean authentication(String user, String pw, Person p) {
		if (registeredUsers.containsKey(user)) {
			UserData userData = registeredUsers.get(user).getData();
			if (pw == userData.getPw() && registeredUsers.get(user).equals(p)) {
				return true;
			}
		}
		return false;
	}

	public Person getPerson(String user) {
		return registeredUsers.get(user);
	}

	public Map<String, Person> getRegisteredUsers() {
		return Collections.unmodifiableMap(registeredUsers);
	}

}
