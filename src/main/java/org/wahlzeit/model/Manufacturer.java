package org.wahlzeit.model;

public class Manufacturer {
	
	private String name;

	/**
	 * @methodtype get
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 * @param name the name to set
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		this.name = name;
	}

}
