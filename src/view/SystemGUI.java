package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.Employee;
import model.SystemDB;

public class SystemGUI extends JFrame implements ActionListener, TableModelListener {
    
    private SystemDB db;
    private List<Employee> employeeList;
    private Object[][] currTableData;

    /** For Serialization.*/
    private static final long serialVersionUID = 6515753873615102989L;
    

    
    /** Buttons for reserving parking spots and updating employees. */
    private JButton viewEmployeesButton, resVisitorBtn, resEmployeeBtn, updateEmployeeBtn, addParkingLot, addEmployee, addParkingSpace;
    
    /** Table that contains information from database. */
    private JTable staffTable, parkingSpotTable;
    
    /** Main panels that divide the GUI window. */
    private JPanel upperPnl, pnlContent, staffTblePnl, pkSpotTblPnl;

    public SystemGUI()  {
        super("ParkingApplication");
        
        updateEmployeeTable();       
        createComponents();
        setVisible(true);
        setMinimumSize(new Dimension(700, 600));
    }
    
    /** Updates current list of employees and updates the currentTable shown. */
    private void updateEmployeeTable() {
         db = new SystemDB();
        try
        {
            employeeList = db.getEmployees();
            
            currTableData = new Object[employeeList.size()][4];
            for (int i=0; i<employeeList.size(); i++) {
                currTableData[i][0] = employeeList.get(i).getmEmpNumber();
                currTableData[i][1] = employeeList.get(i).getmName();
                currTableData[i][2] = employeeList.get(i).getmExtNum();
                currTableData[i][3] = employeeList.get(i).getmVehicleLicense();
                
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    /** Creates the GUI components. */
    private void createComponents() {
        
        // Lay out table into two sections
        upperPnl = createUpperPanel();
        pnlContent = new JPanel();
        pnlContent.add(createEmployeeTablePanel());
        
        this.add(upperPnl, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
                 
    }
    
    /** Creates upper panel with buttons. */
    private JPanel createUpperPanel() {
        
        upperPnl = new JPanel();
        JPanel buttonPanel1 = new JPanel(new GridLayout(1, 4, 5, 5));
        JPanel buttonPanel2 = new JPanel(new GridLayout(1, 3, 5, 5));
        
        //settings its layout to have a capacity of 2 rows and 3 columns
        // 5 is the space between the buttons
        upperPnl.setLayout(new GridLayout(2,1,5,5));
        
        viewEmployeesButton = new JButton("View Employees");
        viewEmployeesButton.addActionListener(this);
        buttonPanel1.add(viewEmployeesButton);
        
        // Reserve Visitor Parking Spot Button
        resVisitorBtn = new JButton("Reserve Visitor Parking");
        resVisitorBtn.addActionListener(this);
        buttonPanel1.add(resVisitorBtn);
        
        // Employee Visitor Parking Spot Button
        resEmployeeBtn = new JButton("Reserve Employee Parking");
        resEmployeeBtn.addActionListener(this);
        buttonPanel1.add(resEmployeeBtn);
        
        // Update Employee information Button
        updateEmployeeBtn = new JButton("Update Employee");
        updateEmployeeBtn.addActionListener(this);
        buttonPanel1.add(updateEmployeeBtn);
        
        //Add parking lot button
        
        addParkingLot = new JButton("Add parking lot");
        addParkingLot.addActionListener(this);
        buttonPanel2.add(addParkingLot);
        
        //Add employee
        addEmployee = new JButton("Add employee");
        addEmployee.addActionListener(this);
        buttonPanel2.add(addEmployee);
        
        //Add parking space
        addParkingSpace = new JButton("Add parking space");
        addParkingSpace.addActionListener(this);
        buttonPanel2.add(addParkingSpace);
        
        upperPnl.add(buttonPanel1);
        upperPnl.add(buttonPanel2);
        
        return upperPnl;
    }
    
    /** Creates panel with table of staff. */
    private JPanel createEmployeeTablePanel() {
        String staffColumnNames[] = {"Employee Number", "Employee Name", "Extension Number", "Vehicle License Number"};
        JPanel pnl = new JPanel();
        staffTable = new JTable(currTableData, staffColumnNames);
        JScrollPane scrollPane = new JScrollPane(staffTable);
        pnl.add(scrollPane);
        staffTable.getModel().addTableModelListener(this);
        
        return pnl;
    }
    
    /** Shows the panel needed for reserving a parking spot for visitors. */
    private JPanel createResVisitorPnl() {
        JPanel visInfoPanel = new JPanel(new GridLayout(6, 0));
        String visLabels[] = {"Visitor's Vehicle License Number: ",  "Sponsor's Employee ID", "Choose Parking Spot", "Choose Date", "Choose Times"};
        JTextField fields[] = new JTextField[2];
        
        for (int i=0; i<visLabels.length; i++) {
            JPanel panel = new JPanel(new GridLayout(1, 2));
            JLabel label = new JLabel(visLabels[i]);
            panel.add(label);
            if (i < 2) {
                fields[i] = new JTextField(25);
                panel.add(fields[i]);
            }            
            visInfoPanel.add(panel);
        }
        
        JButton makeResBtn = new JButton("Make Reservation");
        makeResBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Create reservation class, make DB call
                JOptionPane.showMessageDialog(null, fields[0]);               
            }
            
        });
        
        visInfoPanel.add(makeResBtn);
        
        return visInfoPanel;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resVisitorBtn) 
        {
            pnlContent.removeAll();
            pnlContent.add(createResVisitorPnl());
            pnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == resEmployeeBtn)
        {

        }
        else if (e.getSource() == updateEmployeeBtn)
        {

        }else if (e.getSource() == addParkingLot)
        {

        }
        else if (e.getSource() == addEmployee)
        {

        } else if (e.getSource() == addParkingSpace) {
        	
        }
        else if (e.getSource() == viewEmployeesButton) 
        {
            pnlContent.removeAll();
            updateEmployeeTable();
            pnlContent.add(createEmployeeTablePanel());
            pnlContent.revalidate();
            this.repaint();
        }

    }

    @Override
    public void tableChanged(TableModelEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    
}
