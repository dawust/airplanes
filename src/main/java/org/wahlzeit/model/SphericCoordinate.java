package org.wahlzeit.model;

import java.util.HashMap;

import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Subclass;

/**
 * Spheric representation of a coordinate
 * @author Daniel Wust
 *
 */

public class SphericCoordinate extends AbstractCoordinate {

	// Stores instances of spheric coordinates
	protected static HashMap<String, SphericCoordinate> allSphericCoordinates = new HashMap<String, SphericCoordinate>();	
	
	public final static double EARTH_MEAN_RADIUS = 6371.0;

	private final double latitude;	
	private final double longitude;
	private final double radius;
	
	/**
	 * Retrieves or creates instance of a SphericCoordinate
	 * Convenience method with radius set to earth radius
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @methodtype factory
	 */
	public static SphericCoordinate getInstance(double longitude, double latitude) {
		return SphericCoordinate.getInstance(longitude, latitude, EARTH_MEAN_RADIUS);
	}
	
	/**
	 * Retrieves or creates instance of a SphericCoordinate
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @param radius Radius
	 * @methodtype factory
	 */
	public static SphericCoordinate getInstance(double longitude, double latitude, double radius) {
		String key = SphericCoordinate.asString(longitude, latitude, radius);
		SphericCoordinate result = allSphericCoordinates.get(key);
		if (result == null) {
			synchronized(allSphericCoordinates) {
				result = allSphericCoordinates.get(key);
				if (result == null) {
					result = new SphericCoordinate(longitude, latitude, radius);
					allSphericCoordinates.put(key, result);
				}
			}
		}
		
		return result;
	}
		
	/**
	 * Coordinate constructor by specifying its latitude and longitude
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @param radius Radius
	 * @methodtype constructor
	 * @throws IllegalArgumentException if latitude, longitude or radius are out of boundaries
	 */
	protected SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
		//preconditions
		assertLatitude(latitude);
		assertLongitude(longitude);
		assertRadius(radius);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * Calculate distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getDistance(SphericCoordinate pos) throws IllegalArgumentException {
		//preconditions
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
		
		//postconditions
		assert (distance >= 0.0);
		
		return distance;		
	}
	
	/**
	 * Calculate latitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Latitudinal distance between this and another Coordinate object
	 * @methodtype get
	 */
	public double getLatitudinalDistance(SphericCoordinate pos) throws IllegalArgumentException {
		//preconditions
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
		//preconditions
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
	public CartesianCoordinate toCartesian() {
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
		//preconditions
		assertCoordinate(pos);
		return equals(pos);
	}
	
	/**
	 * Sanity check for latitude
	 * @param latitude latitude
	 * @throws IllegalArgumentException if latitude is out of boundaries
	 * @methodtype assert
	 */
	private void assertLatitude(double latitude) throws IllegalArgumentException{
		if (!(latitude >= -90.0 && latitude <= 90.0)) {
			throw new IllegalArgumentException("Latitude out of boundaries");
		}
	}
	
	/**
	 * Sanity check for longitude
	 * @param longitude longitude
	 * @throws IllegalArgumentException if longitude is out of boundaries
	 * @methodtype assert
	 */
	private void assertLongitude(double longitude) throws IllegalArgumentException{
		if (!(longitude > -180.0 && longitude <= 180.0)) {
			throw new IllegalArgumentException("Longitude out of boundaries");
		}
	}
	
	/**
	 * Sanity check for radius
	 * @param radius radius
	 * @throws IllegalArgumentException if radius is out of boundaries
	 * @methodtype assert
	 */
	private void assertRadius(double radius) throws IllegalArgumentException{
		if (!(radius >= 0.0)) {
			throw new IllegalArgumentException("Radius out of boundaries");
		}
	}
	
	/**
	 * Assert class invariants
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assert (radius >= 0.0);
		assert (longitude > -180.0 && longitude <= 180.0);
		assert (latitude >= -90.0 && latitude <= 90.0);
	}
	
	/**
	 * String representation of a specific coordinate
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @param radius Radius
	 * @methodtype conversion
	 */
	protected static String asString(double latitude, double longitude, double radius) {
		return "SphericCoordinate latitude: " + latitude + "longitude: " + longitude + "radius: " + radius;
	}
	
	protected String asString() {
		return SphericCoordinate.asString(this.latitude, this.longitude, this.radius);
	}

}
