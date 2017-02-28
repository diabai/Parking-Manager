package model;

/**
 * Employee class.
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class Employee {

    /** The employee's ID number. */
	private int mEmpNumber;
	
	/** The employee's name. */
	private String mName;
	
	/** The employee's extension number. */
	private int mExtNum;
	
	/** The employee's vehicle license number. */
	private String mVehicleLicense;
		
	/**
	 * Constructor for employee object
	 * @param mEmpNumber the employee's identification number.
	 * @param mName the employee's name.
	 * @param mExtNum the employee's extension number.
	 * @param mVehicleLicense the employee's license plate number.
	 */
	public Employee(String mName, int mExtNum, String mVehicleLicense) {
		this.mName = mName;
		this.mExtNum = mExtNum;
		this.mVehicleLicense = mVehicleLicense;
	}

	public int getmEmpNumber() {
		return mEmpNumber;
	}

	public void setmEmpNumber(int mEmpNumber) {
		this.mEmpNumber = mEmpNumber;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmExtNum() {
		return mExtNum;
	}

	public void setmExtNum(int mExtNum) {
		this.mExtNum = mExtNum;
	}

	public String getmVehicleLicense() {
		return mVehicleLicense;
	}

	public void setmVehicleLicense(String mVehicleLicense) {
		this.mVehicleLicense = mVehicleLicense;
	}
	 
	
	
	
	
	
	
	
	
}
