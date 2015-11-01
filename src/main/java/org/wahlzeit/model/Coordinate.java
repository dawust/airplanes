package org.wahlzeit.model;

import java.io.Serializable;

/**
 * A coordinate stores a location on the Earth
 */

public class Coordinate implements Serializable {
	
	public static double EARTH_MEAN_RADIUS = 6371.0;
	
	private double latitude = 0.0;	
	private double longitude = 0.0;
	
	/**
	 * Default Coordinate constructor with latitude and longitude = 0.0
	 */
	public Coordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}
	
	/**
	 * Coordinate constructor by specifying its latitude and longitude
	 * @param longitude Longitude
	 * @param latitude Latitude
	 * @methodtype constructor
	 * @throws IllegalArgumentException if latitude or longitude are out of boundaries
	 */
	public Coordinate(double latitude, double longitude) throws IllegalArgumentException {
		// Do sanity check for latitude and longitude
		if (!(latitude >= -90.0 && latitude <= 90.0)) {
			throw new IllegalArgumentException("Latitude out of boundaries");
		}
		if (!(longitude > -180.0 && longitude <= 180.0)) {
			throw new IllegalArgumentException("Longitude out of boundaries");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Coordinate constructor by specifying another Coordinate object
	 * @param pos Coordinate
	 * @methodtype constructor
	 * @throws IllegalArgumentException if pos is null
	 */
	public Coordinate(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		this.latitude = pos.getLatitude();
		this.longitude = pos.getLongitude();
	}
	
	/**
	 * Calculate distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Distance between this and another Coordinate object
	 * @methodtype get
	 * @throws IllegalArgumentException if pos is null
	 */
	public double getDistance(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		
		double radLatitude = Math.toRadians(this.latitude);
		double radPosLatitude = Math.toRadians(pos.latitude);
		double radLonDistance = Math.toRadians(getLongitudinalDistance(pos));

		double distance = EARTH_MEAN_RADIUS * Math.acos(
				(Math.sin(radLatitude) * Math.sin(radPosLatitude))
				+ (Math.cos(radLatitude) * Math.cos(radPosLatitude) * Math.cos(radLonDistance)));
		
		return distance;		
	}
	
	/**
	 * Calculate latitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Latitudinal distance between this and another Coordinate object
	 * @methodtype get
	 * @throws IllegalArgumentException if pos is null
	 */
	public double getLatitudinalDistance(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		
		double latDistance = Math.abs(this.latitude - pos.latitude);
		return latDistance;
	}
	
	/**
	 * Calculate longitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Longitudinal distance between this and another Coordinate object
	 * @methodtype get
	 * @throws IllegalArgumentException if pos is null
	 */
	public double getLongitudinalDistance(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
        	return false;
        } else {
            return equals((Coordinate) o);
        }
    }

    public boolean equals(Coordinate o) {
        if (o == null) {
            return false;
        } else {
            return ((latitude == o.getLatitude()) && longitude == o.getLongitude());
        }
    }
    
    @Override
    public int hashCode() {
        return Double.valueOf(this.latitude).hashCode() + Double.valueOf(this.longitude).hashCode();
    }

}
