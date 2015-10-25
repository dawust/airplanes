package org.wahlzeit.model;

import java.io.Serializable;

/**
 * A coordinate stores the geolocation of a photo
 */

public class Coordinate implements Serializable {
	
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
	 * @throws IllegalArgumentException if latitude or longitude are out of boundaries
	 */
	public Coordinate(double latitude, double longitude) throws IllegalArgumentException {
		// Do sanity check for latitude and longitude
		if (latitude < -90.0 || latitude > 90.0) {
			throw new IllegalArgumentException("Latitude out of boundaries");
		}
		if (longitude < -180.0 || longitude > 180.0) {
			throw new IllegalArgumentException("Longitude out of boundaries");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Coordinate constructor by specifying another Coordinate object
	 * @param pos Coordinate
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
	 * @throws IllegalArgumentException if pos is null
	 */
	public Coordinate getDistance(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		
		double latDistance = Math.abs(this.latitude - pos.latitude);
		double lonDistance = Math.abs(this.longitude - pos.longitude);
		
		return new Coordinate(latDistance, lonDistance);		
	}
	
	/**
	 * Calculate latitudinal distance to another Coordinate object
	 * @param pos Coordinate
	 * @return Latitudinal distance between this and another Coordinate object
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
	 * @throws IllegalArgumentException if pos is null
	 */
	public double getLongitudinalDistance(Coordinate pos) throws IllegalArgumentException {
		if (pos == null) {
			throw new IllegalArgumentException("pos cannot be null");
		}
		
		double lonDistance = Math.abs(this.longitude - pos.longitude);
		return lonDistance;
	}
	
	public double getLatitude() {
		return latitude;
	}

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
