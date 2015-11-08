package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.*;

public class SphericCoordinateTest {

	private SphericCoordinate posErlangen;
	private SphericCoordinate posNuremberg;
	private SphericCoordinate posGreenwich;
	private SphericCoordinate posZero;
	private SphericCoordinate posWest;
	private SphericCoordinate posEast;
	private double delta;
	
	@Before
	public void setUp() {
		posErlangen = new SphericCoordinate(49.11, 11.01);
		posNuremberg = new SphericCoordinate(49.27, 11.04);
		posGreenwich = new SphericCoordinate(51.28, 0.0);
		posZero = new SphericCoordinate(0.0, 0.0);
		posWest = new SphericCoordinate(0.0, -179.0);
		posEast = new SphericCoordinate(0.0, 179.0);
		delta = 0.00001;
	}
	
	@Test
	public void testDefaultConstructor() {
		SphericCoordinate c = new SphericCoordinate();
		assertNotNull(c);
		assertEquals(0.0, c.getLongitude(), delta);
		assertEquals(0.0, c.getLatitude(), delta);
	}
	
	@Test
	public void testConstructorBoundaries() {
		new SphericCoordinate(90, 0);
		new SphericCoordinate(-90, 0);
		new SphericCoordinate(0, 180);
		new SphericCoordinate(0, -179.9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooLarge() {
		new SphericCoordinate(90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooSmall() {
		new SphericCoordinate(-90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooLarge() {
		new SphericCoordinate(0, 180.1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooSmall() {
		new SphericCoordinate(0, -180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNaN() {
		new SphericCoordinate(0, Double.NaN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNull() {
		new SphericCoordinate(null);
	}
	
	@Test
	public void testEquals() {
		assertEquals(posErlangen, posErlangen);
		assertEquals(posErlangen, new SphericCoordinate(posErlangen));
		assertEquals(posZero, new SphericCoordinate());
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

	@Test
	public void testCartesianDistance() {
		assertEquals(17.92426, posErlangen.toCartesian().getDistance(posNuremberg.toCartesian()), delta);
		assertEquals(17.92426, posNuremberg.toCartesian().getDistance(posErlangen.toCartesian()), delta);
	}
	
	@Test
	public void testCartesianToSphericDistance() {
		assertEquals(17.92426, posErlangen.toCartesian().getDistance(posNuremberg), delta);
		assertEquals(17.92426, posNuremberg.toCartesian().getDistance(posErlangen), delta);
	}
	
	@Test
	public void testSphericToCartesianDistance() {
		assertEquals(17.92426, posErlangen.getDistance(posNuremberg.toCartesian()), delta);
		assertEquals(17.92426, posNuremberg.getDistance(posErlangen.toCartesian()), delta);
	}

}
