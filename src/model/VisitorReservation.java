package model;

import java.sql.Date;

/**
 * Contains data about a visitor's parking reservation.  
 * @author Dema
 * @version 2.28.2017
 */
public class VisitorReservation {
    
    /** The visitor's vehicle's license number. */
    private String visitorsVehLicense;
    
    /** The spot number that's been reserved. */
    private int spaceNum;
    
    /** The parking lot where the spot has been reserved at. */
    private String pLName;
    
    /** The ID of the employee sponsoring the visitor. */
    private int empNumber;
    
    /** The ID of the visitor. */
    private int visitorID;
    
    /** The date of the reservation. */
    private Date date;
        
    public VisitorReservation(String theVisitorsVehLicense, int theSpaceNum, String thePLName, int theEmpNumber, 
            Date theDate) {
        visitorsVehLicense = theVisitorsVehLicense;
        spaceNum = theSpaceNum;
        pLName = thePLName;
        empNumber = theEmpNumber;
        visitorID = -1;
        date = theDate;
    }

    /**
     * @return the visitorID
     */
    public int getVisitorID() {
        return visitorID;
    }

    /**
     * @param visitorID the visitorID to set
     */
    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    /**
     * @return the visitorsVehLicense
     */
    public String getVisitorsVehLicense() {
        return visitorsVehLicense;
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

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
  
}
