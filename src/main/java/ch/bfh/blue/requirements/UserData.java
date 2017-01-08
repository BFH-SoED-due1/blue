/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

public interface UserData {

	public abstract String getUserName();

	public abstract String getPw();

	public abstract String getEmail();

	public abstract void setUserName(String userName);

	public abstract void setEmail(String email);

	public abstract void setPw(String email);
}
