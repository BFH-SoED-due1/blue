/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.srs;

import java.util.Date;

public abstract class Person {
	private String name, addresse;
	private Date birthday;

	public Person(String name, String addresse, Date birthday){
		this.name =name;
		this.addresse=addresse;
		this.birthday=birthday;
	}

	public void register(){
		//TODO
	}

	public void login(){
		//TODO
	}

}
