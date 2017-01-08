/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.jpa;

import javax.persistence.Embeddable;

import ch.bfh.blue.requirements.UserData;

@Embeddable
public class UserDataImpl implements UserData {

	private String email;
	private String userName;
	private String pw;

	public UserDataImpl() {

	}

	public UserDataImpl(String email, String userName, String password) {
		this.email = email;
		this.userName = userName;
		this.pw = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPw() {
		return pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
