package model;

import java.sql.Date;
import java.sql.Time;

/** Contains data about a visitor's parking reservation. */
public class VisitorReservation {
    
    private String visitorsVehLicense;
    private int spaceNum;
    private String pLName;
    private int empNumber;
    private int visitorID;
    private Date date;
    private Time time;
        
    public VisitorReservation(String theVisitorsVehLicense, int theSpaceNum, String thePLName, int theEmpNumber, 
            Date theDate, Time theTime) {
        visitorsVehLicense = theVisitorsVehLicense;
        spaceNum = theSpaceNum;
        pLName = thePLName;
        empNumber = theEmpNumber;
        visitorID = -1;
        date = theDate;
        time = theTime;
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

    /**
     * @return the time
     */
    public Time getTime() {
        return time;
    }
    
    
    
}
