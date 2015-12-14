package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.*;

public class CartesianCoordinateTest {

	private CartesianCoordinate posErlangen;
	private CartesianCoordinate posNuremberg;
	private CartesianCoordinate posZero;
	private double delta = 0.0001;
	
	@Before
	public void setUp() {
		posErlangen = CartesianCoordinate.getInstance(4093.7502, 796.4859, 4816.2704);
		posNuremberg = CartesianCoordinate.getInstance(4080.1160, 796.0507, 4827.8979);
		posZero = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
	}
	
	@Test
	public void testDefaultConstructor() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		assertNotNull(c);
		assertEquals(0.0, c.getX(), delta);
		assertEquals(0.0, c.getY(), delta);
		assertEquals(0.0, c.getZ(), delta);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNaN() {
		new CartesianCoordinate(0, Double.NaN, 0);
	}
	
	@Test
	public void testEquals() {
		assertEquals(posErlangen, posErlangen);
		assertEquals(posZero, CartesianCoordinate.getInstance(0.0, 0.0, 0.0));
		assertNotEquals(posNuremberg, posErlangen);
	}
	
	@Test
	public void testDistance() {
		assertEquals(17.92426, posErlangen.getDistance(posNuremberg), delta);
		assertEquals(17.92426, posNuremberg.getDistance(posErlangen), delta);
	}
}
