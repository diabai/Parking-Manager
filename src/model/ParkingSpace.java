package model;


/**
 * 
 * Parking Space class. This class contains the attributes to
 * create a parking space object.
 * @author Ibrahim Diabate
 */
public class ParkingSpace {
	
	
	private int spaceNum;
	private Boolean covered;
	private String pLName;
	
	/**
	 * Constructor for a parking space
	 * @param spaceNum
	 * @param covered
	 * @param pLName
	 */
	public ParkingSpace(int spaceNum, Boolean covered, String pLName) {
		super();
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
	
	
	
	
	

}
