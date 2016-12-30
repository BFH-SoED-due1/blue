package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.service.Controller;

public class ControllerTest {

	@Test
	public void testAtenticationNotNull() throws InstantiationException, IllegalAccessException {
		Controller c = new Controller();
		c.createPerson("muster@muster.ch", "max", "123");
		Person p = c.authentication("max", "123");
		assertNotNull(p);
		c.close();
	}

	@Test
	public void testAtenticationNull() throws InstantiationException, IllegalAccessException {
		Controller c =  new Controller();
		c.createPerson("muster@muster.ch", "max", "123");
		Person p = c.authentication("ma", "123");
		assertNull(p);
		c.close();
	}

	@Test
	public void testMultiController() throws InstantiationException, IllegalAccessException {
		Controller c = new Controller();
		Controller c1 = new Controller();
		Controller c2 = new Controller();
		c.createPerson("muster@muster.ch", "max", "123");
		c2.createPerson("muster@muster.ch", "max1", "1234");
		Person p1 = c.authentication("max", "123");
		Person p2 = c.authentication("max", "123");
		assertNotNull(p1);
		assertNotNull(p2);
		c.close();
	}

	@Test
	public void testGetSpaceOnTime() throws InstantiationException, IllegalAccessException {
		Controller c =  new Controller();
		Person p = c.createPerson("muster@muster.ch", "max", "123");
		Space s1 = c.createSpace("s1", 521);
		Space s2 = c.createSpace("s2", 522);
		Space s3 = c.createSpace("s3", 523);
		c.createReservation(null,p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Space> spList = c.getSpaceOnTime(new Timestamp(1000), new Timestamp(2000));
		assertEquals(s2,spList.get(0));
		assertEquals(s3,spList.get(1));
		c.close();
	}

	@Test
	public void makeTwoEqualsRes() throws InstantiationException, IllegalAccessException {
		Controller c =  new Controller();
		Person p = c.createPerson("muster@muster.ch", "max", "123");
		Space s1 = c.createSpace("s1", 521);
		Space s2 = c.createSpace("s2", 522);
		Space s3 = c.createSpace("s3", 523);
		c.createReservation(null,p, new Timestamp(1000), new Timestamp(2000), s1);
		if (c.isFree(new Timestamp(1000), new Timestamp(2000), s1)){
			c.createReservation(null,p, new Timestamp(1000), new Timestamp(2000), s1);
		}
		c.close();
	}




}
