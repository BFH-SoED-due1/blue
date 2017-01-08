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

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public String getPw() {
		return pw;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void setPw(String pw) {
		this.pw = pw;
	}

}
