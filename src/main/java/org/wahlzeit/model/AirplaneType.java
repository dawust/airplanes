package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import com.google.api.client.util.Data;
import com.googlecode.objectify.annotation.Entity;

/**
 * Implementation of type class for airplane photos
 * @author Daniel Wust
 *
 */

public class AirplaneType {
	private String manufacturer;
	private String model;
	private int capacity;
	private int airspeed;
	
	/**
	 * Constructor with empty default values
	 * @methodtype constructor
	 */
	public AirplaneType() {
		this("", "", 0, 0);
	}
	
	/**
	 * @methodtype constructor
	 * @param manufacturer Manufacturer
	 * @param model Model
	 * @param capacity max passenger capacity
	 * @param airspeed max airspeed
	 */
	public AirplaneType(String manufacturer, String model, int capacity, int airspeed) {
		//preconditions
		assertName(manufacturer);
		assertName(model);
		assertValue(capacity);
		assertValue(airspeed);
		
		this.manufacturer = manufacturer;
		this.model = model;
		this.capacity = capacity;
		this.airspeed = airspeed;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @methodtype set
	 * @param manufacturer the manufacturer to set

	 */
	public void setManufacturer(String manufacturer) {
		//preconditions
		assertName(manufacturer);
		
		this.manufacturer = manufacturer;
		
		//postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @methodtype set
	 * @param model the model to set
	 */
	public void setModel(String model) throws IllegalArgumentException {
		//preconditions
		assertName(model);
		
		this.model = model;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return max airspeed
	 */
	public int getMaxAirspeed() {
		return airspeed;
	}

	/**
	 * @methodtype set
	 * @param airspeed set max airspeed
	 */
	public void setMaxAirspeed(int airspeed) {
		//preconditions
		assertValue(airspeed);
		
		this.airspeed = airspeed;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return max passenger capacity
	 */
	public int getCapacity() {	
		return capacity;
	}

	/**
	 * @methodtype set
	 * @param capacity max passenger capacity to set
	 */
	public void setCapacity(int capacity) {
		//preconditions
		assertValue(capacity);
		
		this.capacity = capacity;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * Sanity check for strings used in this class
	 * Subject to change
	 * @methodtype assert
	 * @param str string to check
	 */
	protected void assertName(String str) {
		if (str == null) {
			throw new IllegalArgumentException("String cannot be null");
		}
	}
	
	/**
	 * Sanity check for integers used in this class
	 * Subject to change
	 * @methodtype assert
	 * @param val value to check
	 */
	protected void assertValue(int val) {
		if (val < 0) {
			throw new IllegalArgumentException("Value must be greater than or equal to 0");
		}
	}
	
	/**
	 * Check class invariants
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		assert(capacity >= 0);
		assert(airspeed >= 0);
	}
	
	/**
	 * String representation of a specific airplane type
	 * @param manufacturer manufacturer
	 * @param model model
	 * @methodtype conversion
	 */
	protected static String asString(String manufacturer, String model) {
		return "AirplaneType manufacturer: " + manufacturer + "model: " + model;
	}
	
	public String asString() {
		return AirplaneType.asString(manufacturer, model);
	}
	
}
