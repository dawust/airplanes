package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class AirplanePhoto extends Photo {
	
	private AirplaneType type;
	
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
	 * @param type set type of the airplane
	 */
	public void setType(AirplaneType type) {
		this.type = type;
	}
	/**
	 * @methodtype get
	 * @return type of the airplane 
	 */
	public AirplaneType getType() {
		return type;
	}
}
