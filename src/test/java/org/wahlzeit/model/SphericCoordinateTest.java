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
	private double delta = 0.0001;
	
	@Before
	public void setUp() {
		posErlangen = SphericCoordinate.getInstance(49.11, 11.01);
		posNuremberg = SphericCoordinate.getInstance(49.27, 11.04);
		posGreenwich = SphericCoordinate.getInstance(51.28, 0.0);
		posZero = SphericCoordinate.getInstance(0.0, 0.0);
		posWest = SphericCoordinate.getInstance(0.0, -179.0);
		posEast = SphericCoordinate.getInstance(0.0, 179.0);
	}
	
	@Test
	public void testDefaultConstructor() {
		SphericCoordinate c = SphericCoordinate.getInstance(0.0, 0.0);
		assertNotNull(c);
		assertEquals(0.0, c.getLongitude(), delta);
		assertEquals(0.0, c.getLatitude(), delta);
	}
	
	@Test
	public void testConstructorBoundaries() {
		SphericCoordinate.getInstance(90, 0);
		SphericCoordinate.getInstance(-90, 0);
		SphericCoordinate.getInstance(0, 180);
		SphericCoordinate.getInstance(0, -179.9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooLarge() {
		SphericCoordinate.getInstance(90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLatTooSmall() {
		SphericCoordinate.getInstance(-90.1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooLarge() {
		SphericCoordinate.getInstance(0, 180.1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLonTooSmall() {
		SphericCoordinate.getInstance(0, -180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNaN() {
		SphericCoordinate.getInstance(0, Double.NaN);
	}
	
	@Test
	public void testEquals() {
		assertEquals(posErlangen, posErlangen);
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
