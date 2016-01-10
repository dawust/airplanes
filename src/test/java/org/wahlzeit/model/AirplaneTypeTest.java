package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AirplaneTypeTest {

	private static AirplaneType airplaneType1;
	private static AirplaneType airplaneType2;
	private static AirplaneType airplaneType3;
	private static AirplaneTypeManager manager;
	
	@BeforeClass
	public static void setUp() {
		manager = AirplaneTypeManager.getInstance();
		airplaneType1 = manager.createAirplaneType("Boeing", "747-8", 605, 920);
		//airplaneType2 = manager.createAirplaneType("Boeing", "787-8", 381, 945);
		//airplaneType3 = manager.createAirplaneType("Airbus", "A380-800", 853, 958);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMultipleInstances() {
		AirplaneType inst1 = manager.createAirplaneType("ABC", "DEF", 0, 1);
		AirplaneType inst2 = manager.createAirplaneType("ABC", "DEF", 1, 2);
	}
	
	@Test
	public void testGetAirplaneType() {
		AirplaneType type = manager.getAirplaneType("Boeing", "747-8");
		assertEquals(airplaneType1, type);
		assertEquals(type.getManufacturer(), "Boeing");
		assertEquals(type.getModel(), "747-8");
		assertEquals(type.getCapacity(), 605);
		assertEquals(type.getMaxAirspeed(), 920);
	}

}
