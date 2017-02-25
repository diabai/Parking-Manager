package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SystemGUI extends JFrame implements ActionListener {

    /** For Serialization.*/
    private static final long serialVersionUID = 6515753873615102989L;
    
    /** Buttons for reserving parking spots and updating employees. */
    private JButton resVisitorBtn, resEmployeeBtn, updateEmployeeBtn, addParkingLot, addEmployee, addParkingSpace;
    
    /** Table that contains information from database. */
    private JTable staffTable, parkingSpotTable;
    
    /** Main panels that divide the GUI window. */
    private JPanel upperPnl, mainPnl, staffTblePnl, pkSpotTblPnl;

    public SystemGUI()  {
        super("ParkingApplication");
        createComponents();
        setVisible(true);
        setMinimumSize(new Dimension(500, 500));
    }
    
    /** Creates the GUI components. */
    private void createComponents() {
        
        // Lay out table into two sections
        upperPnl = createUpperPanel();
        mainPnl = createStaffPanel();
        
        this.add(upperPnl, BorderLayout.NORTH);
        this.add(mainPnl, BorderLayout.CENTER);
                 
    }
    
    /** Creates upper panel with buttons. */
    private JPanel createUpperPanel() {
        
        upperPnl = new JPanel();
        
        //settings its layout to have a capacity of 2 rows and 3 colums
        // 5 is the space between the buttons
        upperPnl.setLayout(new GridLayout(2,3,5,5));
        
        // Reserve Visitor Parking Spot Button
        resVisitorBtn = new JButton("Reserve Visitor Parking");
        resVisitorBtn.addActionListener(this);
        upperPnl.add(resVisitorBtn);
        
        // Employee Visitor Parking Spot Button
        resEmployeeBtn = new JButton("Reserve Employee Parking");
        resEmployeeBtn.addActionListener(this);
        upperPnl.add(resEmployeeBtn);
        
        // Update Employee information Button
        updateEmployeeBtn = new JButton("Update Employee");
        updateEmployeeBtn.addActionListener(this);
        upperPnl.add(updateEmployeeBtn);
        
        //Add parking lot button
        
        addParkingLot = new JButton("Add parking lot");
        addParkingLot.addActionListener(this);
        upperPnl.add(addParkingLot);
        
        //Add employee
        addEmployee = new JButton("Add employee");
        addEmployee.addActionListener(this);
        upperPnl.add(addEmployee);
        
        //Add parking space
        addParkingSpace = new JButton("Add parking space");
        addParkingSpace.addActionListener(this);
        upperPnl.add(addParkingSpace);
        
        return upperPnl;
    }
    
    /** Creates panel with table of staff. */
    private JPanel createStaffPanel() {
        // List panel - blatantly ripped from Professor's code
        staffTblePnl = new JPanel();
        staffTable = new JTable(); // NEEDS DATA
        JScrollPane scrollPane = new JScrollPane(staffTable);
        staffTblePnl.add(scrollPane);
        //staffTable.getModel().addTableModelListener(this);
        
        return staffTblePnl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resVisitorBtn) 
        {
            
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

    }
    
    
}
