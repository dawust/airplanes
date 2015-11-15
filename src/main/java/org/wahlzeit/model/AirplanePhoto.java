package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class AirplanePhoto extends Photo {
	
	private String model;
	private Manufacturer manufacturer;
	
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
	 * @methodtype get
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @methodtype set
	 * @param manufacturer the manufacturer to set
	 * @throws IllegalArgumentException
	 */
	public void setManufacturer(Manufacturer manufacturer) throws IllegalArgumentException {
		if (manufacturer == null) {
			throw new IllegalArgumentException("manufacturer cannot be null");
		}
		this.manufacturer = manufacturer;
	}

	/**
	 * @methodtype get
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @methodtype set
	 * @param model the model to set
	 * @throws IllegalArgumentException
	 */
	public void setModel(String model) throws IllegalArgumentException {
		if (model == null) {
			throw new IllegalArgumentException("model cannot be null");
		}
		this.model = model;
	}


}
