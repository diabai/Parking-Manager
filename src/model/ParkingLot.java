package model;


/**
 *  Parking lot class.
 *  
 * @author Ibrahim Diabate
 *
 */
public class ParkingLot {
	
	private String pLName;
	private String location;
	private int capacity;
	private int numFloors;
	
	public ParkingLot(String pLName, String location, int capacity, int numFloors) {
		super();
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
	
	
	
	
	
	
	

}
