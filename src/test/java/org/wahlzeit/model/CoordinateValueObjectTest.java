package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateValueObjectTest {

	private SphericCoordinate sphericErlangen1;
	private SphericCoordinate sphericErlangen2;
	private SphericCoordinate sphericNuremberg1;
	private SphericCoordinate sphericNuremberg2;
	
	private CartesianCoordinate cartesianErlangen1;
	private CartesianCoordinate cartesianErlangen2;
	private CartesianCoordinate cartesianNuremberg1;
	private CartesianCoordinate cartesianNuremberg2;
	
	
	@Before
	public void setUp() {
		sphericErlangen1 = SphericCoordinate.getInstance(49.11, 11.01);
		sphericErlangen2 = SphericCoordinate.getInstance(49.11, 11.01);
		sphericNuremberg1 = SphericCoordinate.getInstance(49.27, 11.04);
		sphericNuremberg2 = SphericCoordinate.getInstance(49.27, 11.04);
		
		cartesianErlangen1 = CartesianCoordinate.getInstance(4093.7502, 796.4859, 4816.2704);
		cartesianErlangen2 = CartesianCoordinate.getInstance(4093.7502, 796.4859, 4816.2704);
		cartesianNuremberg1 = CartesianCoordinate.getInstance(4080.1160, 796.0507, 4827.8979);
		cartesianNuremberg2 = CartesianCoordinate.getInstance(4080.1160, 796.0507, 4827.8979);
	}
	
	@Test
	public void testSameObject() {
		assertTrue(sphericErlangen1 == sphericErlangen2);
		assertTrue(cartesianErlangen1 == cartesianErlangen2);
		
		assertTrue(sphericNuremberg1 == sphericNuremberg2);
		assertTrue(cartesianNuremberg1 == cartesianNuremberg2);
		
		assertFalse(sphericNuremberg1 == sphericErlangen1);
	}
	
	@Test
	public void testEquals() {
		assertTrue(sphericErlangen1.isEqual(sphericErlangen2));
		assertTrue(sphericErlangen1.isEqual(cartesianErlangen1));

		assertFalse(sphericErlangen1.isEqual(sphericNuremberg1));
	}
	
}
