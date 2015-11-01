package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

public class AirplanePhotoManager extends PhotoManager {

	private static final Logger log = Logger.getLogger(AirplanePhotoManager.class.getName());

	/**
	 * @methodtype command
	 *
	 * Load all persisted airplane photos. Executed when Wahlzeit is restarted.
	 */
	@Override
	public void loadPhotos() {
		Collection<AirplanePhoto> existingPhotos = ObjectifyService.run(new Work<Collection<AirplanePhoto>>() {
			@Override
			public Collection<AirplanePhoto> run() {
				Collection<AirplanePhoto> existingPhotos = new ArrayList<AirplanePhoto>();
				readObjects(existingPhotos, AirplanePhoto.class);
				return existingPhotos;
			}
		});

		for (Photo photo : existingPhotos) {
			if (!doHasPhoto(photo.getId())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load Photo with ID", photo.getIdAsString()).toString());
				loadScaledImages(photo);
				doAddPhoto(photo);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded Photo", photo.getIdAsString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
	}
	
}
