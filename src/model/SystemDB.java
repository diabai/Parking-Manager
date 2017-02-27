package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SystemDB {
    
    private static String userName = "demyan15"; //Change to yours
    private static String password = "ununItHo";
    private static String serverName = "cssgate.insttech.washington.edu";
    private static Connection conn;
    private List<Employee> employeeList;
    private List<ParkingLot> lotList;

    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn =  DriverManager
                .getConnection("jdbc:mysql://" + serverName + "/" 
        + userName + "?user=" + userName + "&password=" + password);
    }

    /**
     * Returns a list of employee objects from the database.
     * @return list of employees
     * @throws SQLException
     */
    public List<Employee> getEmployees() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select empNumber, name, extNum, vehicleLicense "
                + "from employee ";

        employeeList = new ArrayList<Employee>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int ID = rs.getInt("empNumber");
                String name = rs.getString("name");
                int extNum = rs.getInt("extNum");
                String genre = rs.getString("vehicleLicense");
                Employee emp = new Employee(ID, name, extNum, genre);
                employeeList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return employeeList;
    }
    
    /**
     * Returns a list of Parking Location objects from the database.
     * @return list of employees
     * @throws SQLException
     */
    public List<ParkingLot> getParkingLots() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select pLName, location, capacity, numFloors "
                + "from parkingLot ";

        lotList = new ArrayList<ParkingLot>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("pLName");
                String location = rs.getString("location");
                int capacity = rs.getInt("capacity");
                int numFloors = rs.getInt("numFloors");
                ParkingLot emp = new ParkingLot(name, location, capacity, numFloors);
                lotList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return lotList;
    }
    
    /**
     * Returns a list of Parking Location objects from the database.
     * @return list of employees
     * @throws SQLException
     */
    public List<ParkingSpace> getParkingSpaces(ParkingLot lot) throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select spaceNum, covered"
                + " from parkingSpace where pLName = '" + lot.getpLName() + "'";

        List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int number = rs.getInt("spaceNum");
                boolean covered = rs.getBoolean("covered");            
                ParkingSpace emp = new ParkingSpace(number, covered, lot.getpLName());
                spaces.add(emp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return spaces;
    }
    
    /**
     * Modifies the movie information corresponding to the index in the list.
     * @param row index of the element in the list
     * @param columnName attribute to modify
     * @param data value to supply
     */
    public int updateEmployee(int row, String columnName, Object data) {
        
        Employee employee = employeeList.get(row);
        int empNum = employee.getmEmpNumber();
        String sql = "update employee set " + columnName + " = ?  where empNumber = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (data instanceof String)
                preparedStatement.setString(1, (String) data);
            else if (data instanceof Integer)
                preparedStatement.setInt(1, (Integer) data);
            preparedStatement.setInt(2, empNum);
            preparedStatement.executeUpdate();
           employeeList = getEmployees();      
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return -1;
        } 
        return 1;
    }
    
    /**
     * Adds a new reservation to the VisitorReservation table. 
     * @param reservation the reservation to add.
     * @return Returns true or false depending on success..
     */
    public boolean addVisitorReservation(VisitorReservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException();
        }
        String sql = "insert into visitorReservation(visitorsVehLicense, spaceNum, pLName, empNumber, `date`) values "
                + "(?, ?, ?, ?, ?); ";

        if (conn == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, reservation.getVisitorsVehLicense());
            preparedStatement.setInt(2, reservation.getSpaceNum());
            preparedStatement.setString(3, reservation.getpLName());
            preparedStatement.setInt(4, reservation.getEmpNumber());
            preparedStatement.setDate(5, reservation.getDate());
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Adds a new reservation to the EmployeeReservation table. 
     * @param reservation the reservation to add.
     * @return Returns true or false depending on success..
     */
    public boolean addEmployeeReservation(EmployeeReservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException();
        }
        String sql = "insert into empReservation(vehicleLicense, rate, spaceNum, pLName, empNumber) values "
                + "(?, ?, ?, ?, ?); ";

        if (conn == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, reservation.getVehicleLicense());
            preparedStatement.setDouble(2, reservation.getRate());
            preparedStatement.setInt(3, reservation.getSpaceNum());
            preparedStatement.setString(4, reservation.getpLName());
            preparedStatement.setInt(5, reservation.getEmpNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Returns a list of Parking Location objects from the database.
     * @return list of employees
     * @throws SQLException
     */
    public List<EmployeeReservation> getEmpReservations() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select vehicleLicense, rate, spaceNum, pLName, empNumber"
                + " from empReservation";

        List<EmployeeReservation> reservations = new ArrayList<EmployeeReservation>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String license = rs.getString("spaceNum");
                double rate = rs.getDouble("rate");   
                int space = rs.getInt("spaceNum");     
                String pLot = rs.getString("pLName");
                int empNumber = rs.getInt("empNumber");  
                EmployeeReservation emp = new EmployeeReservation(license, rate, space, pLot, empNumber);
                reservations.add(emp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return reservations;
    }
    
    /**
     * Returns a list of Visitor Reservation objects from the database.
     * @return list of Visitor Reservations
     * @throws SQLException
     */
    public List<VisitorReservation> getVisitorReservations() {
        if (conn == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Statement stmt = null;
        String query = "select visitorsVehLicense, spaceNum, pLName, empNumber, `time`, visitorID"
                + " from visitorReservation";

        List<VisitorReservation> reservations = new ArrayList<VisitorReservation>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String license = rs.getString("visitorsVehLicense");
                int space = rs.getInt("spaceNum");     
                String pLot = rs.getString("pLName");
                int empNumber = rs.getInt("empNumber");  
                Date date = rs.getDate("`date`");
                String id = rs.getString("visitorID");
                VisitorReservation reservation = new VisitorReservation(license, space, pLot, empNumber, date);
                reservation.setVisitorID(Integer.parseInt(id));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reservations;
    }
    
}
