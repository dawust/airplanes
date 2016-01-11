package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class AirplanePhoto extends Photo {
	
	private Airplane airplane;
	
	/**
	 * @methodtype constructor
	 */
	public AirplanePhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public AirplanePhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * @methodtype set
	 * @param type set the airplane
	 */
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	/**
	 * @methodtype get
	 * @return the airplane 
	 */
	public Airplane getType() {
		return airplane;
	}
}
