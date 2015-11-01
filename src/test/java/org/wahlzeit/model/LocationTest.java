package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	private Coordinate posErlangen;
	private Coordinate posNuremberg;
	private Location locErlangen;
	private Location locNuremberg;
	private double delta;
	
	@Before
	public void setUp() {
		posErlangen = new Coordinate(49.11, 11.01);
		posNuremberg = new Coordinate(49.27, 11.04);
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
