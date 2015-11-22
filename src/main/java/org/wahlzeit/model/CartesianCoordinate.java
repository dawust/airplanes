package org.wahlzeit.model;

/**
 * Cartesian representation of a coordinate
 * @author Daniel Wust
 *
 */

public class CartesianCoordinate extends AbstractCoordinate {
	
	private double x;
	private double y;
	private double z;
	
	/**
	 * Default Coordinate constructor
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	/**
	 * Coordinate constructor by specifying its cartesian coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @throws IllegalArgumentException if argument is NaN
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
		//preconditions
		assertNotNaN(x);
		assertNotNaN(y);
		assertNotNaN(z);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		//postconditions
		assertClassInvariants();		
	}
	
	/**
	 * Coordinate constructor by specifying another CartesianCoordinate object
	 * @param pos Coordinate
	 * @methodtype constructor
	 */
	public CartesianCoordinate(CartesianCoordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);
		
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		
		//postconditions
		assertClassInvariants();
	}
	
	/**
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return z coordinate
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Calculate distance to another CartesianCoordinate object
	 * @param pos CartesianCoordinate
	 * @return Distance between this and another CartesianCoordinate object
	 * @methodtype get
	 */
	public double getDistance(CartesianCoordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);

		double distance = Math.sqrt(Math.pow((x - pos.x), 2) 
							+ Math.pow((y - pos.y), 2) 
							+ Math.pow((z - pos.z), 2)); 
		
		
		//postconditions
		assert (distance >= 0.0);
		return distance;
	}
		
	/**
	 * Converts Coordinate to CartesianCoordinate object
	 * @return CartesianCoordinate object;
	 * @methodtype conversion
	 */
	protected CartesianCoordinate toCartesian() {
		return this;
	}	
	
	/**
	 * Checks for logic equality between this and another Coordinate object
	 * @param pos Coordinate
	 * @methodtype query
	 */
	public boolean isEqual(CartesianCoordinate pos) throws IllegalArgumentException {
		//preconditions
		assertCoordinate(pos);
		
		return equals(pos);
	}
	
	/**
	 * Asserts that argument is not NaN
	 * @throws IllegalArgumentException if argument is NaN
	 * @methodtype assert
	 */
	private void assertNotNaN(double x) throws IllegalArgumentException {
		if (Double.isNaN(x)) {
			throw new IllegalArgumentException("Argument is NaN");
		}
	}
	
	/**
	 * Assert class invariants
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assert (!Double.isNaN(x));
		assert (!Double.isNaN(y));
		assert (!Double.isNaN(z));
	}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartesianCoordinate)) {
        	return false;
        } else {
        	CartesianCoordinate pos = (CartesianCoordinate) o;
        	return (isEqualDelta(x, pos.getX())
        			&& isEqualDelta(y, pos.getY()) 
        			&& isEqualDelta(z, pos.getZ()));
        }
    }
    
   @Override
    public int hashCode() {
        return Double.valueOf(this.x).hashCode() 
        		+ Double.valueOf(this.y).hashCode() 
        		+ Double.valueOf(this.z).hashCode();
    }

}
