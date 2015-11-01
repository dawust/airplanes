package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.*;

public class CoordinateTest {

	private Coordinate posErlangen;
	private Coordinate posNuremberg;
	private Coordinate posGreenwich;
	private Coordinate posZero;
	private Coordinate posWest;
	private Coordinate posEast;
	private double delta;
	
	@Before
	public void setUp() {
		posErlangen = new Coordinate(49.11, 11.01);
		posNuremberg = new Coordinate(49.27, 11.04);
		posGreenwich = new Coordinate(51.28, 0.0);
		posZero = new Coordinate(0.0, 0.0);
		posWest = new Coordinate(0.0, -179.0);
		posEast = new Coordinate(0.0, 179.0);
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
		new Coordinate(0, -179.9);
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
		new Coordinate(0, -180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNaN() {
		new Coordinate(0, Double.NaN);
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
		assertEquals(17.92426, posErlangen.getDistance(posNuremberg), delta);
		assertEquals(17.92426, posNuremberg.getDistance(posErlangen), delta);
	}
	
	@Test
	public void testLongitudinalDistance() {
		assertEquals(0.0, posGreenwich.getLongitudinalDistance(posZero), delta);
		assertEquals(0.03, posErlangen.getLongitudinalDistance(posNuremberg), delta);
		assertEquals(2.0, posWest.getLongitudinalDistance(posEast), delta);
		assertEquals(2.0, posEast.getLongitudinalDistance(posWest), delta);
	}
	
	@Test
	public void testLatitudinalDistance() {
		assertEquals(51.28, posGreenwich.getLatitudinalDistance(posZero), delta);
		assertEquals(0.16, posErlangen.getLatitudinalDistance(posNuremberg), delta);
	}


}
