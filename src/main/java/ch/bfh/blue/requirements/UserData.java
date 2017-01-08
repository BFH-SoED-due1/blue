/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

public interface UserData {


	/**
	 * returns the user name contained in this userData
	 *
	 * @return
	 */
	public abstract String getUserName();

	/**
	 * returns the password contained in this userData
	 *
	 * @return
	 */
	public abstract String getPw();

	/**
	 * returns the email contained in this userData
	 *
	 * @return
	 */
	public abstract String getEmail();

	/**
	 * sets the user name for this userData
	 *
	 * @param userName
	 */
	public abstract void setUserName(String userName);

	/**
	 * sets the email for this userData
	 *
	 * @param email
	 */
	public abstract void setEmail(String email);

	/**
	 * sets the password for this userData
	 *
	 * @param email
	 */
	public abstract void setPw(String email);
}
