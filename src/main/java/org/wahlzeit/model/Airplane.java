package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * Implementation of type class for airplane photos
 * @author Daniel Wust
 *
 */

public class Airplane extends DataObject {
	
	private static long TOTALID = 0;
	private long id;
	
	private AirplaneType type;
	private String airline;
	private String registration;
	private int age;
	
	/**
	 * Constructor with empty default values
	 * @methodtype constructor
	 */
	public Airplane() {
		this(null, "", "", 0);
	}
	
	/**
	 * @methodtype constructor
	 * @param type type class for airplane
	 * @param airline airline
	 * @param registration registration number
	 * @param age age of airplane
	 */
	public Airplane(AirplaneType type, String airline, String registration, int age) {
		//preconditions
		assertName(airline);
		assertName(registration);
		assertValue(age);
		
		this.type = type;
		this.airline = airline;
		this.registration = registration;
		this.age = age;
		
		this.id = Airplane.TOTALID;
		Airplane.TOTALID++;

		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return type of the airplane
	 */
	public AirplaneType getType() {
		return type;
	}

	/**
	 * @methodtype set
	 * @param airline the airline of the airplane to set
	 */
	public void setType(AirplaneType type) {
		this.type = type;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return airline of the airplane
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @methodtype set
	 * @param airline the airline of the airplane to set
	 */
	public void setAirline(String airline) {
		//preconditions
		assertName(airline);
		
		this.airline = airline;
		
		//postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 * @return registration number of airplane
	 */
	public String getRegistration() {
		return registration;
	}

	/**
	 * @methodtype set
	 * @param registration registration number of airplane
	 */
	public void setRegistration(String registration) throws IllegalArgumentException {
		//preconditions
		assertName(registration);
		
		this.registration = registration;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 * @return age of airplane
	 */
	public int getAge() {	
		return age;
	}

	/**
	 * @methodtype set
	 * @param age age of airplane
	 */
	public void setAge(int age) {
		//preconditions
		assertValue(age);
		
		this.age = age;
		
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
		assert(age >= 0);
	}	
	
	
	/**
	 * Get unique ID of Airplane
	 * @methodtype get
	 */
	public long getID() {
		return id;
	}	
	
	
}
