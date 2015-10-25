package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.*;

public class CoordinateTest {

	private Coordinate posErlangen;
	private Coordinate posNuremberg;
	private Coordinate posGreenwich;
	private Coordinate posZero;
	private double delta;
	
	@Before
	public void setUp() {
		posErlangen = new Coordinate(49.11, 11.01);
		posNuremberg = new Coordinate(49.27, 11.04);
		posGreenwich = new Coordinate(51.28, 0.0);
		posZero = new Coordinate(0.0, 0.0);
		delta = 0.00001;
	}
	
	@Test
	public void testDefaultConstructor() {
		Coordinate c = new Coordinate();
		assertNotNull(c);
		assertEquals(0.0, c.getLongitude(), delta);
		assertEquals(0.0, c.getLatitude(), delta);
	}
	
	@Test
	public void testConstructorBoundaries() {
		new Coordinate(90, 0);
		new Coordinate(-90, 0);
		new Coordinate(0, 180);
		new Coordinate(0, -180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooLarge() {
		new Coordinate(90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooSmall() {
		new Coordinate(-90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooLarge() {
		new Coordinate(0, 180.1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooSmall() {
		new Coordinate(0, -180.1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNull() {
		new Coordinate(null);
	}
	
	@Test
	public void testEquals() {
		assertEquals(posErlangen, posErlangen);
		assertEquals(posErlangen, new Coordinate(posErlangen));
		assertEquals(posZero, new Coordinate());
		assertNotEquals(posZero, posGreenwich);
		assertNotEquals(posNuremberg, posErlangen);
	}
	
	@Test
	public void testDistance() {
		assertEquals(posGreenwich.getDistance(posZero).getLatitude(), 51.28, delta);
		assertEquals(posGreenwich.getDistance(posZero).getLongitude(), 0.0, delta);
		assertEquals(posErlangen.getDistance(posNuremberg).getLatitude(), 0.16, delta);
		assertEquals(posErlangen.getDistance(posNuremberg).getLongitude(), 0.03, delta);
	}
	
	@Test
	public void testLongitudinalDistance() {
		assertEquals(posGreenwich.getLongitudinalDistance(posZero), 0.0, delta);
		assertEquals(posErlangen.getLongitudinalDistance(posNuremberg), 0.03, delta);
	}
	
	@Test
	public void testLatitudinalDistance() {
		assertEquals(posGreenwich.getLatitudinalDistance(posZero), 51.28, delta);
		assertEquals(posErlangen.getLatitudinalDistance(posNuremberg), 0.16, delta);
	}


}
