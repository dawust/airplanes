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
		assertCoordinate(pos);
		
		AbstractCoordinate other = (AbstractCoordinate) pos;
		return this.toCartesian().getDistance(other.toCartesian());
	}
	
	/**
	 * Converts Coordinate to CartesianCoordinate object
	 * @return CartesianCoordinate object;
	 * @methodtype conversion
	 */
	protected abstract CartesianCoordinate toCartesian();
	
	/**
	 * Check if two double values are the same using a delta value
	 * @methodtype query
	 */
	protected boolean isEqualDelta(double d1, double d2) {
		return (Math.abs(d1 - d2) < DELTA);
	}	
	
	/**
	 * Asserts that Coordinate object is not null
	 * @throws IllegalArgumentException if pos is null
	 * @methodtype assert
	 */
	protected void assertCoordinate(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
	}	
}
