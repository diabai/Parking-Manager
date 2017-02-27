package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import model.ParkingLot;
import model.SystemDB;
import model.VisitorReservation;
import model.ParkingSpace;

public class SystemGUI extends JFrame implements ActionListener, TableModelListener {
    
    private SystemDB db;
    private List<Employee> employeeList;
    List<ParkingSpace> spotsList;
    private Object[][] currTableData;

    /** For Serialization.*/
    private static final long serialVersionUID = 6515753873615102989L;
    

    
    /** Buttons for reserving parking spots and updating employees. */
    private JButton viewEmployeesButton, resVisitorBtn, btnAddEmployee, resEmployeeBtn, updateEmployeeBtn, addParkingLot, addEmployee,
	addParkingSpace, btnAddLot, btnAddSpace;
    
    /** Table that contains information from database. */
    private JTable staffTable, parkingSpotTable;
    
    /** Main panels that divide the GUI window. */
    private JPanel upperPnl, pnlContent, staffTblePnl, pkSpotTblPnl;
    
    /** Combo boxes for reserving parking spots. */
    private JComboBox PLlocation, PLSpot, resDate, resTime;
    
    /** Labels for user input*/
	private JLabel[] txfLabel = new JLabel[4];
	/**Text fields for user input*/
	private JTextField[] txfField = new JTextField[4];

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
    
    /**
     * The panel that appears when user clicks Add Employee
     * @return
     */
    private JPanel createAddEmployeePnl() {
    	// Add Panel -- the panel that appears when Add Employee button is
    			// clicked
    			JPanel pnlAdd = new JPanel();
    			pnlAdd.setLayout(new GridLayout(6, 0));
    			String labelNames[] = { "Enter employee number: ", "Enter Name: ", "Enter extension number: " };
    			for (int i = 0; i < labelNames.length; i++) {
    				JPanel panel = new JPanel();
    				txfLabel[i] = new JLabel(labelNames[i]);
    				txfField[i] = new JTextField(25);
    				panel.add(txfLabel[i]);
    				panel.add(txfField[i]);
    				pnlAdd.add(panel);
    			}
    			JPanel panel = new JPanel();
    			btnAddEmployee = new JButton("Add Employee");
    			btnAddEmployee.addActionListener(this);
    			panel.add(btnAddEmployee);
    			pnlAdd.add(panel);
    			// End of panel Add -- put this comment here to ease reading
    			return pnlAdd;
    }
    
    /**
     * Panel that appears when Add Parking lot button is clicked
     * @return
     */
    private JPanel createAddParkingLotPnl() {
    	
    	// Add parking lot panel -- panel that appears when Add Parking lot
    			// button is clicked

    			JPanel pnlAddParkingLot = new JPanel();
    			pnlAddParkingLot.setLayout(new GridLayout(6, 0));
    			String labelsLot[] = { "Enter parking lot name: ", "Enter location: ", "Enter capacity: ",
    					"Enter number of floors: " };
    			for (int i = 0; i < labelsLot.length; i++) {
    				JPanel panelLot = new JPanel();
    				txfLabel[i] = new JLabel(labelsLot[i]);
    				txfField[i] = new JTextField(25);
    				panelLot.add(txfLabel[i]);
    				panelLot.add(txfField[i]);
    				pnlAddParkingLot.add(panelLot);
    			}
    			JPanel panelLot = new JPanel();
    			btnAddLot = new JButton("Add parking lot");
    			btnAddLot.addActionListener(this);
    			panelLot.add(btnAddLot);
    			pnlAddParkingLot.add(panelLot); // End of add parking lot panel
    			return pnlAddParkingLot ;
    	
    }
    
    
    private JPanel createAddParkingSpace() {
    	// Add parking space panel -- panel that appears when Add Parking space
		// button is clicked

		JPanel pnlAddParkingSpace = new JPanel();
		pnlAddParkingSpace.setLayout(new GridLayout(6, 0));
		String labelsSpaces[] = { "Enter parking space number: ", "Enter true if covered, otherwise false ", "Enter parking lot name: ",
				"Enter number of floors: " };
		for (int i = 0; i < labelsSpaces.length; i++) {
			JPanel panelSpaces = new JPanel();
			txfLabel[i] = new JLabel(labelsSpaces[i]);
			txfField[i] = new JTextField(25);
			panelSpaces.add(txfLabel[i]);
			panelSpaces.add(txfField[i]);
			pnlAddParkingSpace.add(panelSpaces);
		}
		JPanel panelSpaces = new JPanel();
		btnAddSpace = new JButton("Add parking space");
		btnAddSpace.addActionListener(this);
		panelSpaces.add(btnAddSpace);
		pnlAddParkingSpace.add(panelSpaces); // End of add parking space panel
		return pnlAddParkingSpace;
    }
    
    /** Shows the panel needed for reserving a parking spot for visitors. */
    private JPanel createResVisitorPnl() {
        JPanel visInfoPanel = new JPanel(new GridLayout(6, 0));
        String visLabels[] = {"Visitor's Vehicle License Number: ",  "Sponsor's Employee ID", "Choose Date", "Choose Times"};
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

        // Set up empty parking lot, space, date, and time combo boxes - will be updated as user progressively makes selections    
        // Parking Lot
        JPanel pLComboPanel = new JPanel();
        pLComboPanel.setLayout(new GridLayout(1, 2));
        List<ParkingLot> lots = new ArrayList<ParkingLot>();
        try {
            lots = db.getParkingLots();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        spotsList = new ArrayList<ParkingSpace>();
        // Convert to an object
        Object[] PLlocations = new Object[lots.size()];
        for (int i = 0; i < lots.size(); i++) {
            PLlocations[i] = lots.get(i);  
        }
        PLlocation = new JComboBox(PLlocations);
        pLComboPanel.add(new JLabel("Choose Parking Location: "));
        PLlocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PLSpot.removeAllItems();
                try {
                    spotsList = db.getParkingSpaces((ParkingLot)PLlocation.getSelectedItem());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                for (int i = 0; i < spotsList.size(); i++) {
                    PLSpot.addItem(spotsList.get(i));  
                }               
            }

        });
        pLComboPanel.add(PLlocation);
        visInfoPanel.add(pLComboPanel);

        // Parking Spot
        JPanel spotsComboPanel = new JPanel();
        spotsComboPanel.setLayout(new GridLayout(1, 2));
        PLSpot = new JComboBox();
        spotsComboPanel.add(new JLabel("Choose Parking Spot: "));
        PLSpot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Maybe will need to check whether something is selected before loading next information?  
                JOptionPane.showConfirmDialog(null, createAvailableTimesPnl());
            }

        });
        spotsComboPanel.add(PLSpot);
        visInfoPanel.add(spotsComboPanel);

        // Date
        JPanel datePnl = new JPanel();
        JTextField date = new JTextField(15);
        datePnl.setLayout(new GridLayout(1, 2));
        datePnl.add(new JLabel("Choose Date: "));
        datePnl.add(date);
        visInfoPanel.add(datePnl);
        
        // Time
        JPanel timesPnl = new JPanel();
        JTextField time = new JTextField(15);
        timesPnl.setLayout(new GridLayout(1, 2));
        timesPnl.add(new JLabel("Choose Time: "));
        timesPnl.add(time);
        visInfoPanel.add(timesPnl);
        
        JButton makeResBtn = new JButton("Make Reservation");
        makeResBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Create reservation class, make DB call
                Date datee = Date.valueOf(date.toString());
                Time timee = Time.valueOf(time.toString());
                int employeeNumber = Integer.parseInt(fields[1].getText());
                VisitorReservation rsvtion = new VisitorReservation(fields[0].getText(), ((ParkingSpace)PLSpot.getSelectedItem()).getSpaceNum(), 
                        ((ParkingLot)PLlocation.getSelectedItem()).getpLName(), employeeNumber, datee, timee);
                db.addVisitorReservation(rsvtion);           
            }
            
        });
        
        visInfoPanel.add(makeResBtn);
        
        return visInfoPanel;
        
    }
    
    /**
     * Displays the reservation times already on the parking spot. 
     * @return
     */
    public JPanel createAvailableTimesPnl() {
        JPanel mainPanel = new JPanel();
        return mainPanel;
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

        }else if (e.getSource() == addParkingLot) {

			pnlContent.removeAll();
			pnlContent.add(createAddParkingLotPnl());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddLot) {

			ParkingLot pl = new ParkingLot(txfField[0].getText(), txfField[1].getText(),
					Integer.parseInt(txfField[2].getText()), Integer.parseInt(txfField[3].getText()));
			db.addParkingLot(pl);
			JOptionPane.showMessageDialog(null, "Added parking lot successfully!");
			for (int i = 0; i < txfField.length; i++) {
				txfField[i].setText("");
			}

		}
		else if (e.getSource() == addEmployee) {
			pnlContent.removeAll();
			pnlContent.add(createAddEmployeePnl());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddEmployee) {
			Employee emp = new Employee(Integer.parseInt(txfField[0].getText()), txfField[1].getText(),
					Integer.parseInt(txfField[2].getText()), txfField[3].getText());
			db.addEmployee(emp);
			JOptionPane.showMessageDialog(null, "Added employee successfully!");
			for (int i = 0; i < txfField.length; i++) {
				txfField[i].setText("");
			}
		}else if (e.getSource() == addParkingSpace) {

			pnlContent.removeAll();
			pnlContent.add(createAddParkingSpace());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddSpace) {
				
			ParkingSpace ps = new ParkingSpace(Integer.parseInt(txfField[0].getText()), Boolean.parseBoolean(txfField[1].getText()),
					txfField[2].getText());
			db.addParkingSpace(ps);;
			JOptionPane.showMessageDialog(null, "Added parking space successfully!");
			for (int i = 0; i < txfField.length; i++) {
				txfField[i].setText("");
			}

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
