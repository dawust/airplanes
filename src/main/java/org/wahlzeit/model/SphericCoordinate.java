package org.wahlzeit.model;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;

/**
 * Spheric representation of a coordinate
 * @author Daniel Wust
 *
 */

public class SphericCoordinate extends AbstractCoordinate {
	
	public static double EARTH_MEAN_RADIUS = 6371.0;

	private double latitude;	
	private double longitude;
	private double radius;
	
	/**
	 * Default Coordinate constructor with latitude and longitude = 0.0
	 */
	public SphericCoordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.radius = EARTH_MEAN_RADIUS;
	}
	
	/**
	 * Convenience constructor with radius set to earth radius
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @methodtype constructor
	 * @throws IllegalArgumentException if latitude, longitude or radius are out of boundaries
	 */
	public SphericCoordinate(double latitude, double longitude) throws IllegalArgumentException {
		this(latitude, longitude, EARTH_MEAN_RADIUS);
	}	
	
	/**
	 * Coordinate constructor by specifying its latitude and longitude
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @param radius Radius
	 * @methodtype constructor
	 * @throws IllegalArgumentException if latitude, longitude or radius are out of boundaries
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
		// Do sanity check for latitude and longitude
		if (!(latitude >= -90.0 && latitude <= 90.0)) {
			throw new IllegalArgumentException("Latitude out of boundaries");
		}
		if (!(longitude > -180.0 && longitude <= 180.0)) {
			throw new IllegalArgumentException("Longitude out of boundaries");
		}
		if (!(radius >= 0.0)) {
			throw new IllegalArgumentException("Radius out of boundaries");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	/**
	 * Coordinate constructor by specifying another Coordinate object
	 * @param pos Coordinate
	 * @methodtype constructor
	 */
	public SphericCoordinate(SphericCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		
		this.latitude = pos.getLatitude();
		this.longitude = pos.getLongitude();
		this.radius = pos.getRadius();
	}
	
	/**
	 * Calculate distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getDistance(SphericCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		
		// Convert to cartesian if we have different radii
		if (this.radius != pos.radius) {
			return this.toCartesian().getDistance(pos.toCartesian()); 
		}
		
		double radLatitude = Math.toRadians(this.latitude);
		double radPosLatitude = Math.toRadians(pos.latitude);
		double radLonDistance = Math.toRadians(getLongitudinalDistance(pos));
		
		double distance = radius * Math.acos(
				(Math.sin(radLatitude) * Math.sin(radPosLatitude))
				+ (Math.cos(radLatitude) * Math.cos(radPosLatitude) * Math.cos(radLonDistance)));
		
		return distance;		
	}
	
	/**
	 * Calculate latitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Latitudinal distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getLatitudinalDistance(SphericCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		
		double latDistance = Math.abs(this.latitude - pos.latitude);
		return latDistance;
	}
	
	/**
	 * Calculate longitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Longitudinal distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getLongitudinalDistance(SphericCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		
		double lonDistance = Math.abs(this.longitude - pos.longitude);
		
		if (lonDistance >= 180.0) {
			lonDistance = 360.0 - lonDistance;
		}
		
		return lonDistance;
	}
	
	/**
	 * @return Latitude of coordinate object
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @return Longitude of coordinate object
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * @return Radius of coordinate object
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Converts Coordinate to CartesianCoordinate object
	 * @return CartesianCoordinate object;
	 * @methodtype conversion
	 */
	protected CartesianCoordinate toCartesian() {
		double radLatitude = Math.toRadians(latitude);
		double radLongitude = Math.toRadians(longitude);
		double x = radius * Math.cos(radLatitude) * Math.cos(radLongitude);
		double y = radius * Math.cos(radLatitude) * Math.sin(radLongitude);
		double z = radius * Math.sin(radLatitude);
		return new CartesianCoordinate(x,y,z);
	}	
	
	/**
	 * Checks for logic equality between this and another Coordinate object
	 * @param pos Coordinate
	 * @methodtype query
	 */
	public boolean isEqual(SphericCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		return equals(pos);
	}
			
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SphericCoordinate)) {
        	return false;
        } else {
        	SphericCoordinate pos = (SphericCoordinate) o;
	    	return (isEqualDelta(latitude, pos.getLatitude())
    			&& isEqualDelta(longitude, pos.getLongitude()) 
    			&& isEqualDelta(radius, pos.getRadius())
    			&& isEqualDelta(radius, pos.radius));	
        }
    }
    
    @Override
    public int hashCode() {
        return Double.valueOf(this.latitude).hashCode() 
        		+ Double.valueOf(this.longitude).hashCode() 
        		+ Double.valueOf(this.radius).hashCode();
    }



}