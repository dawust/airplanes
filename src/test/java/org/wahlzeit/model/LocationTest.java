package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	private SphericCoordinate posErlangen;
	private SphericCoordinate posNuremberg;
	private Location locErlangen;
	private Location locNuremberg;
	private double delta;
	
	@Before
	public void setUp() {
		posErlangen = new SphericCoordinate(49.11, 11.01);
		posNuremberg = new SphericCoordinate(49.27, 11.04);
		locErlangen = new Location("Erlangen");
		locNuremberg = new Location("Nuremberg", posNuremberg);
		delta = 0.00001;
	}
	
	@Test
	public void testCoordinateGetter() {
		assertEquals(posNuremberg, locNuremberg.getCoordinate());
	}
	
	@Test
	public void testCoordinateSetter() {
		locErlangen.setCoordinate(posErlangen);
		assertEquals(posErlangen, locErlangen.getCoordinate());
	}
	
	@Test
	public void testNameGetter() {
		assertEquals("Nuremberg", locNuremberg.getName());
		assertEquals("Erlangen", locErlangen.getName());
	}
}
