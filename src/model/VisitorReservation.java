package model;

/** Contains data about a visitor's parking reservation. */
public class VisitorReservation {
    
    private String visitorsVehLicense;
    private int spaceNum;
    private int pLName;
    private int empNumber;
    private int visitorID;
    private String date;
    private String time;
        
    public VisitorReservation(String theVisitorsVehLicense, int theSpaceNum, int thePLName, int theEmpNumber, 
            int theVisitorID, String theDate, String theTime) {
        visitorsVehLicense = theVisitorsVehLicense;
        spaceNum = theSpaceNum;
        pLName = thePLName;
        empNumber = theEmpNumber;
        visitorID = theVisitorID;
        date = theDate;
        time = theTime;
    }
    
}
