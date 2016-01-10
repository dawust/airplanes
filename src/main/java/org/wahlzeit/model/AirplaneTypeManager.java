package org.wahlzeit.model;

import java.util.HashMap;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class AirplaneTypeManager  {

	protected static final AirplaneTypeManager instance = new AirplaneTypeManager();
	@Container 
	protected HashMap<String, AirplaneType> typeMap = new HashMap<String, AirplaneType>();

	/**
	 * @methodtype constructor
	 */
	public AirplaneTypeManager() {
		
	}

	/**
	 * Returns singleton instance of manager
	 * @methodtype get
	 */
	public static final AirplaneTypeManager getInstance() {
		return instance;
	}
	
	/**
	 * Creates new AirplaneType
	 * @param manufacturer manufacturer
	 * @param model model
	 * @param capacity max passenger capacity
	 * @param airspeed max airspeed
	 * @throws IllegalArgumentException if type with same manufacturer and model already exists
	 * @methodtype factory
	 */
	public synchronized AirplaneType createAirplaneType(String manufacturer, String model, int capacity, int airspeed) {
		AirplaneType type = getAirplaneType(manufacturer, model);
		if (type != null) {
			throw new IllegalArgumentException("AirplaneType with this manufacturer and model already exists");
		}
		
		type = new AirplaneType(manufacturer, model, capacity, airspeed);
		typeMap.put(type.asString(), type);
		
		return type;
	}
	
	/**
	 * Returns AirplaneType for a specified manufacturer and model if it exists
	 * @methodtype get
	 */
	public AirplaneType getAirplaneType(String manufacturer, String model) {
		return typeMap.get(AirplaneType.asString(manufacturer, model));
	}
}

