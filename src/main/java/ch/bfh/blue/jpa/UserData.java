/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import javax.persistence.Embeddable;


@Embeddable
public class UserData {

	private String email;
	private String userName;
	private String pw;

	public UserData(String email,String userName, String password){
		this.email=email;
		this.userName=userName;
		this.pw=password;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPw() {
		return pw;
	}

}
