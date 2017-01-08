/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.UserData;

public class UserDataTest {

	@Test
	public void testSetterAndGetter() {
		UserData data = new UserDataImpl();
		String email = "muster@muster.ch";
		String pw= "123";
		String userName= "max";
		data.setEmail(email);
		data.setPw(pw);
		data.setUserName(userName);
		assertEquals(email,data.getEmail());
		assertEquals(pw,data.getPw());
		assertEquals(userName,data.getUserName());
	}

}
