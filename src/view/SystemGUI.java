package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
            mainPnl.removeAll();
            mainPnl.add(createResVisitorPnl());
            mainPnl.revalidate();
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

    }
    
    
}
