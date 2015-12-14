package org.wahlzeit.model;

import java.util.HashMap;

/**
 * Cartesian representation of a coordinate
 * @author Daniel Wust
 *
 */

public class CartesianCoordinate extends AbstractCoordinate {
	
	
	// Stores instances of cartesian coordinates
	protected static HashMap<String, CartesianCoordinate> allCartesianCoordinates = new HashMap<String, CartesianCoordinate>();	
	
	private final double x;
	private final double y;
	private final double z;
	
	/**
	 * Retrieves or creates instance of a CartesianCoordinate
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @methodtype factory
	 */
	public static CartesianCoordinate getInstance(double x, double y, double z) {
		String key = CartesianCoordinate.asString(x, y, z);
		CartesianCoordinate result = allCartesianCoordinates.get(key);
		if (result == null) {
			synchronized(allCartesianCoordinates) {
				result = allCartesianCoordinates.get(key);
				if (result == null) {
					result = new CartesianCoordinate(x, y, z);
					allCartesianCoordinates.put(key, result);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Coordinate constructor by specifying its cartesian coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @throws IllegalArgumentException if argument is NaN
	 * @methodtype constructor
	 */
	protected CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
		//preconditions
		assertNotNaN(x);
		assertNotNaN(y);
		assertNotNaN(z);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		//postconditions
		assertClassInvariants();		
	}
		
	/**
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return z coordinate
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Calculate distance to another CartesianCoordinate object
	 * @param pos CartesianCoordinate
	 * @return Distance between this and another CartesianCoordinate object
	 * @methodtype get
	 */
	public double getDistance(CartesianCoordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);

		double distance = Math.sqrt(Math.pow((x - pos.x), 2) 
							+ Math.pow((y - pos.y), 2) 
							+ Math.pow((z - pos.z), 2)); 
		
		
		//postconditions
		assert (distance >= 0.0);
		return distance;
	}
		
	/**
	 * Converts Coordinate to CartesianCoordinate object
	 * @return CartesianCoordinate object;
	 * @methodtype conversion
	 */
	public CartesianCoordinate toCartesian() {
		return this;
	}	
	
	/**
	 * Asserts that argument is not NaN
	 * @throws IllegalArgumentException if argument is NaN
	 * @methodtype assert
	 */
	private void assertNotNaN(double x) throws IllegalArgumentException {
		if (Double.isNaN(x)) {
			throw new IllegalArgumentException("Argument is NaN");
		}
	}
	
	/**
	 * Assert class invariants
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assert (!Double.isNaN(x));
		assert (!Double.isNaN(y));
		assert (!Double.isNaN(z));
	}
	
	/**
	 * String representation of a specific coordinate
	 * @param x x
	 * @param y y
	 * @param z z
	 * @methodtype conversion
	 */
	protected static String asString(double x, double y, double z) {
		return "CartesianCoordinate x: " + x + "y: " + y + "z: " + z;
	}
	
	protected String asString() {
		return CartesianCoordinate.asString(this.x, this.y, this.z);
	}

}
