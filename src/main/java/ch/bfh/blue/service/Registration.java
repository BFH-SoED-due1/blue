/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.UserDataImpl;

public class Registration {
	private Map<String, Mieter> registeredUsers = new HashMap<>();

	public void register(UserDataImpl data) {
		registeredUsers.put(data.getUserName(), new Mieter(data));
	}

	public boolean login(String userName, String password, Mieter m) {
		return authentication(userName, password, m);
	}

	public boolean authentication(String user, String pw, Mieter m) {
		if (registeredUsers.containsKey(user)) {
			UserDataImpl userData = registeredUsers.get(user).getData();
			if (pw == userData.getPw() && registeredUsers.get(user).equals(m)) {
				return true;
			}
		}
		return false;
	}

	public Mieter getMieter(String user) {
		return registeredUsers.get(user);
	}

	public Map<String, Mieter> getRegisteredUsers() {
		return Collections.unmodifiableMap(registeredUsers);
	}

}
