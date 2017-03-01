package model;

/**
 *  Parking lot class.
 * @author Ibrahim Diabate
 * @version 2.28.2017
 */
public class ParkingLot {
	
    /** Name of the parking lot. */
	private String pLName;
	
	/** Location of the parking lot. */
	private String location;
	
	/** Capacity of the parking lot. */
	private int capacity;
	
	/** Number of floors in the parking lot. */
	private int numFloors;
	
	public ParkingLot(String pLName, String location, int capacity, int numFloors) {
		this.pLName = pLName;
		this.location = location;
		this.capacity = capacity;
		this.numFloors = numFloors;
	}

	public String getpLName() {
		return pLName;
	}

	public void setpLName(String pLName) {
		this.pLName = pLName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}
	
	@Override
	public String toString() {
	    return "Name: " + pLName + ", Location: " + location;
	}
	
	
	
	
	
	
	

}
