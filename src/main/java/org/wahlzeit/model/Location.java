package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Location extends DataObject {
	private Coordinate coordinate;
	private String name;

	/**
	 * @methodtype constructor
	 * @param name the name to set
	 * @throws IllegalArgumentException
	 */
	public Location(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		
		this.name = name;
	}
	
	/**
	 * @methodtype constructor
	 * @param name the name to set
	 * @param coordinate the coordinate to set
	 * @throws IllegalArgumentException
	 */
	public Location(String name, Coordinate coordinate) throws IllegalArgumentException {
		this(name);
		
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate cannot be null");
		}
		this.coordinate = coordinate;
	}
	
	/**
	 * @methodtype get
	 * @return the name
	 */
	public String getName() {
		return name;
	}
		
	/**
	 * @methodtype get
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * @methodtype set
	 * @param coordinate the coordinate to set
	 * @throws IllegalArgumentException
	 */
	public void setCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate cannot be null");
		}
		this.coordinate = coordinate;
	}
	
	
}
