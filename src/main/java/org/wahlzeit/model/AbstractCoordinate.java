package org.wahlzeit.model;

/**
 * Abstract superclass for different representations of a coordinate
 * @author Daniel Wust
 *
 */

public abstract class AbstractCoordinate implements Coordinate {
	
	public static double DELTA = 0.0001;

	/**
	 * Checks for logic equality between this and another Coordinate object
	 * Implementation uses conversion to CartesianCoordinate 
	 * @param pos Coordinate
	 * @methodtype query
	 */
	public boolean isEqual(Coordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);
		
		AbstractCoordinate other = (AbstractCoordinate) pos;
		return this.toCartesian().isEqual(other.toCartesian());
	}
	
	/**
	 * Calculate distance to another Coordinate object
	 * Implementation uses conversion to CartesianCoordinate 
	 * @param pos Coordinate
	 * @return Distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getDistance(Coordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);
		
		AbstractCoordinate other = (AbstractCoordinate) pos;
		double distance = this.toCartesian().getDistance(other.toCartesian());
		
		//postconditions
		assert (distance >= 0.0);
		
		return distance;
	}
	
	/**
	 * Converts Coordinate to CartesianCoordinate object
	 * @return CartesianCoordinate object;
	 * @methodtype conversion
	 */
	protected abstract CartesianCoordinate toCartesian();
		
	/**
	 * Asserts that Coordinate object is not null and a subclass of AbstractCoordinate
	 * @throws IllegalArgumentException if pos is null
	 * @throws IllegalArgumentException if pos is not of type AbstractCoordinate
	 * @methodtype assert
	 */
	protected void assertCoordinate(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		if (!(pos instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("pos must be of type AbstractCoordinate");
		}
	}	
	
	/**
	 * Assert class invariants
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		// Define class invariants for abstract class here
	};
	
	/**
	 * Check if two double values are the same using a delta value
	 * @methodtype query
	 */
	protected boolean isEqualDelta(double d1, double d2) {
		return (Math.abs(d1 - d2) < DELTA);
	}	
}
