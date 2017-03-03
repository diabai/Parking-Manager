package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

import model.Employee;
import model.EmployeeReservation;
import model.ParkingLot;
import model.SystemDB;
import model.VisitorReservation;
import model.ParkingSpace;

/**
 * Creates the GUI interface.
 * 
 * @author Dema and Ibrahim
 * @version 2.28.2017
 */
public class SystemGUI extends JFrame implements ActionListener {

	/** For Serialization. */
	private static final long serialVersionUID = 6515753873615102989L;

	/** The database object that provides access to SQL database. */
	private SystemDB db;

	/** List of employees. Used as needed. */
	private List<Employee> employeeList;

	/** List of parking spots. Used as needed. */
	private List<ParkingSpace> spotsList;

	/** List of employee reservations. Used as needed. */
	private List<EmployeeReservation> empResList;

	/** Contains list of employees as Object types to pass to a table. */
	private Object[][] staffTableData;

	/** Buttons for reserving parking spots and updating employees. */
	private JButton viewEmployeesButton, resVisitorBtn, btnAddEmployee, resEmployeeBtn, updateEmployeeBtn,
			addParkingLot, addEmployee, addParkingSpace, btnAddLot, btnAddSpace;

	/** Table that contains information from database. */
	private JTable staffTable;

	/** Main panels that divide the GUI window. */
	private JPanel upperPnl, pnlContent;

	/** Combo boxes for reserving parking spots. */
	private JComboBox PLlocation, PLSpot;

	/** Labels for user input */
	private JLabel[] txfLabel = new JLabel[4];

	/** Text fields for user input */
	private JTextField[] txfField = new JTextField[4];

	/** Labels for user input */
	private JLabel[] txfEmpLabel = new JLabel[3];

	/** Text fields for user input */
	private JTextField[] txfEmpField = new JTextField[3];

	/** Boolean for determining whether to show pane. */
	private boolean initialSelection;

	public SystemGUI() {
		super("ParkingApplication");

		updateEmployeeTable();
		createComponents();
		setVisible(true);
		setMinimumSize(new Dimension(700, 550));
	}

	/** Updates current list of employees and updates the table shown. */
	private void updateEmployeeTable() {
		db = new SystemDB();
		try {
			employeeList = db.getEmployees();

			staffTableData = new Object[employeeList.size()][4];
			for (int i = 0; i < employeeList.size(); i++) {
				staffTableData[i][0] = employeeList.get(i).getmEmpNumber();
				staffTableData[i][1] = employeeList.get(i).getmName();
				staffTableData[i][2] = employeeList.get(i).getmExtNum();
				staffTableData[i][3] = employeeList.get(i).getmVehicleLicense();

			}

		} catch (SQLException e) {
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
		JPanel buttonPanel1 = new JPanel(new GridLayout(1, 4, 15, 5));
		JPanel buttonPanel2 = new JPanel(new GridLayout(1, 3, 5, 5));

		// settings its layout to have a capacity of 2 rows and 3 columns
		// 5 is the space between the buttons
		upperPnl.setLayout(new GridLayout(2, 1, 5, 5));

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

		// Add parking lot button

		addParkingLot = new JButton("Add parking lot");
		addParkingLot.addActionListener(this);
		buttonPanel2.add(addParkingLot);

		// Add employee
		addEmployee = new JButton("Add employee");
		addEmployee.addActionListener(this);
		buttonPanel2.add(addEmployee);

		// Add parking space
		addParkingSpace = new JButton("Add parking space");
		addParkingSpace.addActionListener(this);
		buttonPanel2.add(addParkingSpace);

		upperPnl.add(buttonPanel1);
		upperPnl.add(buttonPanel2);

		return upperPnl;
	}

	/** Creates panel with table of staff. */
	private JPanel createEmployeeTablePanel() {
		String staffColumnNames[] = { "Employee Number", "Employee Name", "Extension Number",
				"Vehicle License Number" };
		JPanel pnl = new JPanel();
		staffTable = new JTable(staffTableData, staffColumnNames);
		JScrollPane scrollPane = new JScrollPane(staffTable);
		pnl.add(scrollPane);

		return pnl;
	}

	/**
	 * The panel that appears when user clicks Add Employee
	 * 
	 * @return JPanel with buttons and fields attached
	 */
	private JPanel createAddEmployeePnl() {
		// Add Panel -- the panel that appears when Add Employee button is
		// clicked
		JPanel pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(6, 0));
		String labelNames[] = { "Enter Name: ", "Enter extension number: ", "Enter vehicle license #: " };
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfEmpLabel[i] = new JLabel(labelNames[i]);
			txfEmpField[i] = new JTextField(25);
			panel.add(txfEmpLabel[i]);
			panel.add(txfEmpField[i]);
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
	 * 
	 * @return JPanel with buttons and fields attached.
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
		return pnlAddParkingLot;

	}

	/**
	 * Panel that appears when Add Parking space button is clicked
	 * 
	 * @return JPanel with functionality added in.
	 */
	private JPanel createAddParkingSpace() {

		JPanel pnlAddParkingSpace = new JPanel();
		pnlAddParkingSpace.setLayout(new GridLayout(6, 0));
		String labelsSpaces[] = { "Enter parking space number: ", "Enter true if covered, otherwise false ",
				"Enter parking lot name: " };
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

	/**
	 * Creates the panel needed for reserving a parking spot for visitors.
	 * 
	 * @return JPanel with functionality added in.
	 */
	private JPanel createResVisitorPnl() {
		JPanel visInfoPanel = new JPanel(new GridLayout(6, 1, 15, 15));

		JPanel LicensePanel = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Visitor's Vehicle License Number: ");
		LicensePanel.add(label);
		JTextField licenseField = new JTextField(25);
		LicensePanel.add(licenseField);
		visInfoPanel.add(LicensePanel);

		// Set up empty parking lot, space, and date combo boxes - will be
		// updated as user progressively makes selections
		// Parking Lot
		JPanel pLComboPanel = new JPanel();
		pLComboPanel.setLayout(new GridLayout(1, 2));
		List<ParkingLot> lots = new ArrayList<ParkingLot>();
		try {
			lots = db.getParkingLots();
			empResList = db.getEmpReservations();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		spotsList = new ArrayList<ParkingSpace>();
		initialSelection = true;
		// Convert to an object
		Object[] PLlocations = new Object[lots.size()];
		for (int i = 0; i < lots.size(); i++) {
			PLlocations[i] = lots.get(i);
		}
		PLlocation = new JComboBox(PLlocations);
		pLComboPanel.add(new JLabel("Choose Parking Location: "));
		PLlocation.addActionListener(new ActionListener() { // Should update the
															// list of spots to
															// choose from
			@Override
			public void actionPerformed(ActionEvent e) {
				PLSpot.removeAllItems();
				try {
					spotsList = db.getParkingSpaces((ParkingLot) PLlocation.getSelectedItem());
					if (empResList != null) {
						Iterator<ParkingSpace> iter = spotsList.iterator();
						while (iter.hasNext()) {
							ParkingSpace space = iter.next();
							for (EmployeeReservation reservation : empResList) {
								if (space.getpLName().equals(reservation.getpLName())
										&& space.getSpaceNum() == reservation.getSpaceNum()) {
									iter.remove();
								}
							}
						}
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < spotsList.size(); i++) {
					PLSpot.addItem(spotsList.get(i));
				}
				if (!initialSelection) {
					JOptionPane.showMessageDialog(null, createReservedTimesPnl(db.getVisitorReservations(),
							(ParkingLot) PLlocation.getSelectedItem()));
				} else {
					initialSelection = false;
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
		if (PLlocation.getItemCount() > 0) {
			PLlocation.setSelectedIndex(0);
		}
		spotsComboPanel.add(PLSpot);
		visInfoPanel.add(spotsComboPanel);

		// Date
		JPanel datePnl = new JPanel();
		JTextField date = new JTextField(15);
		datePnl.setLayout(new GridLayout(1, 2));
		datePnl.add(new JLabel("Choose Date (YYYY-MM-DD): "));
		datePnl.add(date);
		visInfoPanel.add(datePnl);

		// Button that gathers all information and adds information to the
		// database
		JPanel btnPanel = new JPanel(new GridLayout(1, 3));
		JButton makeResBtn = new JButton("Make Reservation");
		makeResBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create reservation class, make DB call
				if (licenseField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter a valid license number");
				} else {
					try {
						VisitorReservation rsvtion = new VisitorReservation(licenseField.getText(),
								((ParkingSpace) PLSpot.getSelectedItem()).getSpaceNum(),
								((ParkingLot) PLlocation.getSelectedItem()).getpLName(),
								employeeList.get(staffTable.getSelectedRow()).getmEmpNumber(),
								Date.valueOf(date.getText()));
						if (db.addVisitorReservation(rsvtion)) {
							JOptionPane.showMessageDialog(null, "Spot successfully reserved");
						} else {
							JOptionPane.showMessageDialog(null, "Failed to reserve");
						}
					} catch (IllegalArgumentException ee) {
						JOptionPane.showMessageDialog(null, "Date incorrectly entered");
					}
					catch (SQLException ee)
					{
					    JOptionPane.showMessageDialog(null, "Failed to reserve");
					}
					catch (NullPointerException ee)
                    {
                        JOptionPane.showMessageDialog(null, "Failed to reserve");
                    }
				}
			}

		});
		btnPanel.add(new JPanel());
		btnPanel.add(new JPanel());
		btnPanel.add(makeResBtn);
		visInfoPanel.add(btnPanel);

		return visInfoPanel;
	}

	/**
	 * Shows the panel needed for reserving a parking spot for employees.
	 * 
	 * @return JPanel with functionality added in.
	 */
	private JPanel createResEmployeePnl() {
		// Get selected Employee from table
		JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));

		List<ParkingLot> lots = new ArrayList<ParkingLot>();

		try {
			lots = db.getParkingLots();
			empResList = db.getEmpReservations();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		spotsList = new ArrayList<ParkingSpace>();

		JPanel comboPanel1 = new JPanel(new GridLayout(1, 2));
		// Convert to an object
		Object[] PLlocations = new Object[lots.size()];
		for (int i = 0; i < lots.size(); i++) {
			PLlocations[i] = lots.get(i);
		}
		PLlocation = new JComboBox(PLlocations);
		comboPanel1.add(new JLabel("Choose Parking Location: "));
		PLlocation.addActionListener(new ActionListener() { // Update parking
															// spots to those in
															// the chosen
															// parking lot
			@Override
			public void actionPerformed(ActionEvent e) {
				PLSpot.removeAllItems();
				try {
					spotsList = db.getParkingSpaces((ParkingLot) PLlocation.getSelectedItem());
					Iterator<ParkingSpace> iter = spotsList.iterator();
					if (empResList != null) {
						while (iter.hasNext()) {
							ParkingSpace space = iter.next();

							for (EmployeeReservation reservation : empResList) {
								if (space.getpLName().equals(reservation.getpLName())
										&& space.getSpaceNum() == reservation.getSpaceNum()) {
									iter.remove();
								}
							}
						}
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < spotsList.size(); i++) {
					PLSpot.addItem(spotsList.get(i));
				}
			}

		});
		comboPanel1.add(PLlocation);
		panel.add(comboPanel1);

		// Parking Spot
		JPanel spotsComboPanel = new JPanel();
		spotsComboPanel.setLayout(new GridLayout(1, 2));
		PLSpot = new JComboBox();
		spotsComboPanel.add(new JLabel("Choose Parking Spot: "));
		if (PLlocation.getItemCount() > 0) {
			PLlocation.setSelectedIndex(0);
		}
		spotsComboPanel.add(PLSpot);
		panel.add(spotsComboPanel);

		// Rate
		JPanel ratePanel = new JPanel(new GridLayout(1, 2));
		JLabel rateLabel = new JLabel("Enter Rate: ");
		JTextField rateField = new JTextField(10);
		ratePanel.add(rateLabel);
		ratePanel.add(rateField);
		panel.add(ratePanel);

		// Button that gathers information and enters it into the database
		JPanel btnPanel = new JPanel(new GridLayout(1, 3));
		JButton makeResBtn = new JButton("Make Reservation");
		makeResBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create reservation class, make DB call
				try {
					double rate = Double.parseDouble(rateField.getText());
					EmployeeReservation rsvtion = new EmployeeReservation(
							employeeList.get(staffTable.getSelectedRow()).getmVehicleLicense(), rate,
							((ParkingSpace) PLSpot.getSelectedItem()).getSpaceNum(),
							((ParkingLot) PLlocation.getSelectedItem()).getpLName(),
							employeeList.get(staffTable.getSelectedRow()).getmEmpNumber());
					db.addEmployeeReservation(rsvtion);
					JOptionPane.showMessageDialog(null, "Spot successfully reserved");
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(null, "Failed to reserve the spot; rate format is incorrect");
				}
				catch (SQLException ee)
                {
                    JOptionPane.showMessageDialog(null, "Failed to reserve");
                }
				catch (NullPointerException ee)
                {
                    JOptionPane.showMessageDialog(null, "Failed to reserve");
                }

			}

		});
		btnPanel.add(new JPanel());
		btnPanel.add(new JPanel());
		btnPanel.add(makeResBtn);
		panel.add(btnPanel);

		return panel;

	}

	/**
	 * Creates a panel that lists all of the reserved times for a parking lot.
	 * 
	 * @param reservations
	 *            The current visitor reservations.
	 * @param lot
	 *            The selected Lot.
	 * @return a JPanel with attached functionality.
	 */
	private JPanel createReservedTimesPnl(List<VisitorReservation> reservations, ParkingLot lot) {
		JPanel mainPnl = new JPanel(new GridLayout(reservations.size(), 1));
		int totalReservations = 0;
		for (VisitorReservation res : reservations) {
			if (res.getpLName().equals(lot.getpLName())) {
				mainPnl.add(new JLabel("Parking Spot: " + res.getSpaceNum() + " Date: " + res.getDate()));
				totalReservations++;
			}
		}
		if (totalReservations == 0) {
			mainPnl.add(new JLabel("No Reservations"));
		}
		return mainPnl;
	}

	/**
	 * Will create a panel for updating employees.
	 * 
	 * @return JPanel with buttons and fields attached.
	 */
	private JPanel createUpdEmpPnl() {
		JPanel mainPanel = new JPanel(new GridLayout(3, 1));
		JPanel telPnl = new JPanel(new GridLayout(1, 2));
		JPanel LicPnl = new JPanel(new GridLayout(1, 2));

		// Extension modification
		JLabel telLabel = new JLabel("New Ext #: ");
		JTextField telField = new JTextField(15);
		telPnl.add(telLabel);
		telPnl.add(telField);
		mainPanel.add(telPnl);

		// License # modification
		JLabel LicLabel = new JLabel("New License #: ");
		JTextField LicField = new JTextField(30);
		LicPnl.add(LicLabel);
		LicPnl.add(LicField);
		mainPanel.add(LicPnl);

		// Gathers entered data and updates database
		JPanel btnPanel = new JPanel(new GridLayout(1, 3));
		JButton makeResBtn = new JButton("Make Update");
		makeResBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create reservation class, make DB call
				if (telField.getText().length() > 0) {
					try {
						int success = db.updateEmployee(staffTable.getSelectedRow(), "extNum",
								Integer.parseInt(telField.getText()));
						if (success > 0) {
							JOptionPane.showMessageDialog(null, "Extension # Successfully Changed");
						} else {
							JOptionPane.showMessageDialog(null, "Failed To Change Extension #");
						}
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(null, "Extension number is incorrect");
					}

				}

				if (LicField.getText().length() > 0) {
					int success = db.updateEmployee(staffTable.getSelectedRow(), "vehicleLicense", LicField.getText());
					if (success > 0) {
						JOptionPane.showMessageDialog(null, "vehicleLicense # Successfully Changed");
					} else {
						JOptionPane.showMessageDialog(null, "Failed To Change vehicleLicense #");
					}
				}
			}

		});
		btnPanel.add(new JPanel());
		btnPanel.add(new JPanel());
		btnPanel.add(makeResBtn);
		mainPanel.add(btnPanel);

		return mainPanel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resVisitorBtn) {
			if (staffTable.getSelectedRow() > -1) {
				pnlContent.removeAll();
				pnlContent.add(createResVisitorPnl());
				pnlContent.revalidate();
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Must select Employee first");
			}
		} else if (e.getSource() == resEmployeeBtn) {
			// Check whether an employee has been chosen
			if (staffTable.getSelectedRow() > -1) {
				pnlContent.removeAll();
				pnlContent.add(createResEmployeePnl());
				pnlContent.revalidate();
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Must select Employee first");
			}
		} else if (e.getSource() == updateEmployeeBtn) {
			// Check whether an employee has been chosen
			if (staffTable.getSelectedRow() > -1) {
				pnlContent.removeAll();
				pnlContent.add(createUpdEmpPnl());
				pnlContent.revalidate();
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Must select Employee first");
			}
		} else if (e.getSource() == addParkingLot) // ***
		{
			pnlContent.removeAll();
			pnlContent.add(createAddParkingLotPnl());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddLot) { // ***
			Boolean isValid = true;
			try {
				txfField[0].getText();
				txfField[1].getText();
				Integer.parseInt(txfField[2].getText());
				Integer.parseInt(txfField[3].getText());
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null,
						"Please make sure you entered correct value for all fields");
				isValid = false;
			}
			if (isValid) {
			ParkingLot pl = null;
			try {
				pl = new ParkingLot(txfField[0].getText(), txfField[1].getText(),
						Integer.parseInt(txfField[2].getText()), Integer.parseInt(txfField[3].getText()));
				db.addParkingLot(pl);
				JOptionPane.showMessageDialog(null, "Added parking lot successfully!");
				for (int i = 0; i < txfField.length; i++) {
					txfField[i].setText("");
				}
			} catch (SQLException ee) {
				JOptionPane.showMessageDialog(null,
						"Failed to Add Parking Lot\nPlease make sure you entered correct value for all fields");
			}
			}
		} else if (e.getSource() == addEmployee) { // *
			pnlContent.removeAll();
			pnlContent.add(createAddEmployeePnl());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddEmployee) { // *
			Boolean isValid = true;
			try {
				txfEmpField[0].getText();
				Integer.parseInt(txfEmpField[1].getText());
				txfEmpField[2].getText();
				 
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null,
						"Please make sure you entered correct value for all fields");
				isValid = false;
			}

			if (isValid) {
			Employee emp = new Employee(txfEmpField[0].getText(), Integer.parseInt(txfEmpField[1].getText()),
					txfEmpField[2].getText());
			db.addEmployee(emp);
			JOptionPane.showMessageDialog(null, "Added employee successfully!");
			for (int i = 0; i < txfEmpField.length; i++) {
				txfEmpField[i].setText("");
			}
			}
		} else if (e.getSource() == addParkingSpace) { // **

			pnlContent.removeAll();
			pnlContent.add(createAddParkingSpace());
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddSpace) { // **

			Boolean isValid = true;
			try {
				Integer.parseInt(txfField[0].getText());
				Boolean.parseBoolean(txfField[1].getText());
				 txfField[2].getText();
				 
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null,
						"Please make sure you entered correct value for all fields");
				isValid = false;
			}

			if (isValid) {
			ParkingSpace ps = new ParkingSpace(Integer.parseInt(txfField[0].getText()),
					Boolean.parseBoolean(txfField[1].getText()), txfField[2].getText());
			try {
				db.addParkingSpace(ps);
				JOptionPane.showMessageDialog(null, "Added parking space successfully!");
				for (int i = 0; i < txfField.length; i++) {
					txfField[i].setText("");
				}
			} catch (SQLException ee) {
				JOptionPane.showMessageDialog(null,
						"Error adding Parking Space. \nMake sure the parking lot name that you entered exist");
			}
			}
		} else if (e.getSource() == viewEmployeesButton) {
			pnlContent.removeAll();
			updateEmployeeTable();
			pnlContent.add(createEmployeeTablePanel());
			pnlContent.revalidate();
			this.repaint();
		}

	}

}
