package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate posErlangenCartesian;
	private Coordinate posNurembergCartesian;
	
	private Coordinate posErlangenSpheric;
	private Coordinate posNurembergSpheric;

	private double delta = 0.0001;
	
	@Before
	public void setUp() {
		
		posErlangenCartesian = CartesianCoordinate.getInstance(4093.7502, 796.4859, 4816.2704);
		posNurembergCartesian = CartesianCoordinate.getInstance(4080.1160, 796.0507, 4827.8979);
		
		posErlangenSpheric = SphericCoordinate.getInstance(49.11, 11.01);
		posNurembergSpheric = SphericCoordinate.getInstance(49.27, 11.04);
	}
	
	
	@Test
	public void testIsEqual() {	
		assertTrue(posErlangenSpheric.isEqual(posErlangenSpheric));
		assertTrue(posNurembergSpheric.isEqual(posNurembergSpheric));
		
		assertTrue(posErlangenCartesian.isEqual(posErlangenCartesian));
		assertTrue(posNurembergCartesian.isEqual(posNurembergCartesian));
		
		assertFalse(posErlangenSpheric.isEqual(posNurembergSpheric));
		assertFalse(posNurembergSpheric.isEqual(posErlangenSpheric));
		
		assertFalse(posErlangenCartesian.isEqual(posNurembergCartesian));
		assertFalse(posNurembergCartesian.isEqual(posErlangenCartesian));	
	}
	
	@Test
	public void testIsEqualCartesianSpheric() {
		assertTrue(posErlangenCartesian.isEqual(posErlangenSpheric));
		
		assertFalse(posErlangenCartesian.isEqual(posNurembergSpheric));
		assertFalse(posNurembergCartesian.isEqual(posErlangenSpheric));
	}
	
	@Test
	public void testIsEqualSphericCartesian() {
		assertTrue(posErlangenSpheric.isEqual(posErlangenCartesian));
		
		assertFalse(posErlangenSpheric.isEqual(posNurembergCartesian));
		assertFalse(posNurembergSpheric.isEqual(posErlangenCartesian));
	}	
	
	@Test
	public void testDistance() {
		assertEquals(17.92426, posErlangenCartesian.getDistance(posNurembergCartesian), delta);
		assertEquals(17.92426, posNurembergCartesian.getDistance(posErlangenCartesian), delta);
		
		assertEquals(17.92426, posErlangenSpheric.getDistance(posNurembergSpheric), delta);
		assertEquals(17.92426, posNurembergSpheric.getDistance(posErlangenSpheric), delta);
	}
	
	@Test
	public void testDistanceCartesianSpheric() {
		assertEquals(17.92426, posErlangenCartesian.getDistance(posNurembergSpheric), delta);
		assertEquals(17.92426, posNurembergCartesian.getDistance(posErlangenSpheric), delta);
	}
	
	@Test
	public void testDistanceSphericCartesian() {
		assertEquals(17.92426, posErlangenSpheric.getDistance(posNurembergCartesian), delta);
		assertEquals(17.92426, posNurembergSpheric.getDistance(posErlangenCartesian), delta);
	}
	
}
