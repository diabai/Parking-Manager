package model;

/** Contains information about an employee's parking reservation. */
public class EmployeeReservation {
    
    private String vehicleLicense;
    private double rate;
    private int spaceNum;
    private String pLName;
    private int empNumber;
    
    public EmployeeReservation(String theVehicleLicense, double theRate, int theSpaceNum, String thePLName, int theEmpNumber) {
        vehicleLicense = theVehicleLicense;
        rate = theRate;
        spaceNum = theSpaceNum;
        pLName = thePLName;
        empNumber = theEmpNumber;
    }

}
