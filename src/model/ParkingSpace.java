package model;

/**
 * Parking Space class. This class contains the attributes to
 * create a parking space object.
 * @author Ibrahim Diabate
 *  @version 2.28.2017
 */
public class ParkingSpace {
	
    /** Spot number. */
	private int spaceNum;
	
	/** Whether this spot is under cover or not. */
	private Boolean covered;
	
	/** The name of the parking lot this spot is in. */
	private String pLName;
	
	/**
	 * Constructor for a parking space
	 * @param spaceNum Spot number.
	 * @param covered Whether this spot is under cover or not.
	 * @param pLName The name of the parking lot this spot is in.
	 */
	public ParkingSpace(int spaceNum, Boolean covered, String pLName) {
		this.spaceNum = spaceNum;
		this.covered = covered;
		this.pLName = pLName;
	}

	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	public Boolean getCovered() {
		return covered;
	}

	public void setCovered(Boolean covered) {
		this.covered = covered;
	}

	public String getpLName() {
		return pLName;
	}

	public void setpLName(String pLName) {
		this.pLName = pLName;
	}
	
	@Override
	public String toString() {
	    return "Number: " + spaceNum + ", Covered: " + covered;
	}
	
	
	

}
