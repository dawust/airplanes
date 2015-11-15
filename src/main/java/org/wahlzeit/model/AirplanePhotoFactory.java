package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class AirplanePhotoFactory extends PhotoFactory {
	
	
	protected AirplanePhotoFactory() {
		// do nothing
	}
	
	/**
	 * Creates a new photo 
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new AirplanePhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new AirplanePhoto(id);
	}
	
}
