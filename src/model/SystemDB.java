package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SystemDB {
    
    private static String userName = "demyan15"; //Change to yours
    private static String password = "ununItHo";
    private static String serverName = "cssgate.insttech.washington.edu";
    private static Connection conn;
    private List<Employee> list;

    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                + serverName + "/", connectionProps);

        System.out.println("Connected to database");
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
                + "from demyan15.employee ";

        list = new ArrayList<Employee>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int ID = rs.getInt("empNumber");
                String name = rs.getString("name");
                int extNum = rs.getInt("extNum");
                String genre = rs.getString("vehicleLicense");
                Employee emp = new Employee(ID, name, extNum, genre);
                list.add(emp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return list;
    }
    
    /**
     * Modifies the movie information corresponding to the index in the list.
     * @param row index of the element in the list
     * @param columnName attribute to modify
     * @param data value to supply
     */
    public void updateEmployee(int row, String columnName, Object data) {
        
        Employee employee = list.get(row);
        int empNum = employee.getmEmpNumber();
        String sql = "update demyan15.employee set " + columnName + " = ?  where empNumber = ? ";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (data instanceof String)
                preparedStatement.setString(1, (String) data);
            else if (data instanceof Integer)
                preparedStatement.setInt(1, (Integer) data);
            preparedStatement.setInt(2, empNum);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
        
    }
    
}
