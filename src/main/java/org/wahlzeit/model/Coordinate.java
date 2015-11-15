package org.wahlzeit.model;

/**
 * Interface for a coordinate
 * @author Daniel Wust
 *
 */
public interface Coordinate {
	
	/**
	 * Calculate distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Distance between this and another Coordinate object
	 * @throws IllegalArgumentException if pos is null
	 * @methodtype get
	 */
	public double getDistance(Coordinate pos);
	
	/**
	 * Checks if the positions of this and another Coordinate object are equal
	 * @param pos Coordinate
	 * @methodtype query
	 */
	public boolean isEqual(Coordinate pos);
}
