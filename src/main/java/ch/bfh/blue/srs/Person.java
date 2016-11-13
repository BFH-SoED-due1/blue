/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.srs;



public abstract class Person {
	private UserData data;

	public Person(UserData data){
		this.data = data;
	}

	public boolean login(String userName,String password){
		return Registration.authentication(userName, password,this);
	}

	public UserData getData() {
		return this.data;
	}

}
