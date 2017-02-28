package model;

/** 
 * Contains information about an employee's parking reservation.
 * @author Dema
 * @version 2.28.2017
 */
public class EmployeeReservation {
    
    /** The license number of the employee. */
    private String vehicleLicense;
    
    /** The rate for this reservation. */
    private double rate;
    
    /** The spot reserved. */
    private int spaceNum;
    
    /** The name of the Parking Lot where the spot has been reserved. */
    private String pLName;
    
    /** The employee's ID number. */
    private int empNumber;
    
    public EmployeeReservation(String theVehicleLicense, double theRate, int theSpaceNum, String thePLName, int theEmpNumber) {
        vehicleLicense = theVehicleLicense;
        rate = theRate;
        spaceNum = theSpaceNum;
        pLName = thePLName;
        empNumber = theEmpNumber;
    }

    /**
     * @return the vehicleLicense
     */
    public String getVehicleLicense() {
        return vehicleLicense;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @return the spaceNum
     */
    public int getSpaceNum() {
        return spaceNum;
    }

    /**
     * @return the pLName
     */
    public String getpLName() {
        return pLName;
    }

    /**
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }

}
