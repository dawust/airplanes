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
		if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
			throw new IllegalArgumentException("Argument is NaN");
		}
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Coordinate constructor by specifying another CartesianCoordinate object
	 * @param pos Coordinate
	 * @methodtype constructor
	 */
	public CartesianCoordinate(CartesianCoordinate pos) throws IllegalArgumentException {
		assertCoordinate(pos);
		
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
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
		assertCoordinate(pos);

		double res = Math.sqrt(Math.pow((x - pos.x), 2) 
							+ Math.pow((y - pos.y), 2) 
							+ Math.pow((z - pos.z), 2)); 
		return res;
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
		assertCoordinate(pos);
		
		return equals(pos);
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
