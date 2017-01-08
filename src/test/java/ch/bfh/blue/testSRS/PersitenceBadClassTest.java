/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import org.junit.AfterClass;
import org.junit.Test;

import ch.bfh.blue.requirements.AbstractPersistencManager;

public class PersitenceBadClassTest {

	@AfterClass
    public static void runOnceAfterClass() {
        AbstractPersistencManager.setSclass("ch.bfh.blue.jpa.PersistenceManger");
    }

	@Test (expected=RuntimeException.class)
	public void testClassNotFoaund() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager.setSclass("BADCLASS");
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
	}

}
