import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PM_GUI {

	private JFrame frmPasswordManager;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JTextField connectionTextField;
	private JTextField addUsernameTextField;
	private JTextField addPasswordTextField;
	private JTextField findUsernameTextField;
	private JTextField findPasswordTextField;
	
	private boolean connectedToDatabase = false;
	private int option = 0;
	private Connection connection;

	private ResultSetTableModel tableModel;
	private JTable resultTable;
	private JTextField addServiceTextField;
	private JTextField findServiceTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PM_GUI window = new PM_GUI();
					window.frmPasswordManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PM_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordManager = new JFrame();
		frmPasswordManager.setForeground(Color.WHITE);
		frmPasswordManager.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		frmPasswordManager.setTitle("Password Manager");
		frmPasswordManager.getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		frmPasswordManager.getContentPane().setBackground(Color.DARK_GRAY);
		frmPasswordManager.getContentPane().setForeground(Color.GRAY);
		frmPasswordManager.setBounds(100, 100, 857, 492);
		frmPasswordManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordManager.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Welcome to My Password Manager!");
		titleLabel.setForeground(Color.LIGHT_GRAY);
		titleLabel.setBackground(Color.LIGHT_GRAY);
		titleLabel.setFont(new Font("Unispace", Font.BOLD, 20));
		titleLabel.setBounds(233, 11, 387, 41);
		frmPasswordManager.getContentPane().add(titleLabel);
		
		JLabel passwordLabel = new JLabel("Enter your password:");
		passwordLabel.setForeground(Color.LIGHT_GRAY);
		passwordLabel.setBackground(Color.YELLOW);
		passwordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		passwordLabel.setBounds(10, 104, 129, 14);
		frmPasswordManager.getContentPane().add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBackground(Color.LIGHT_GRAY);
		usernameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		usernameTextField.setBounds(168, 71, 204, 20);
		frmPasswordManager.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Enter your username:");
		usernameLabel.setForeground(Color.LIGHT_GRAY);
		usernameLabel.setBackground(Color.YELLOW);
		usernameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		usernameLabel.setBounds(10, 74, 129, 14);
		frmPasswordManager.getContentPane().add(usernameLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBackground(Color.LIGHT_GRAY);
		passwordTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(168, 102, 204, 20);
		frmPasswordManager.getContentPane().add(passwordTextField);
		
		connectionTextField = new JTextField();
		connectionTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		connectionTextField.setEditable(false);
		connectionTextField.setForeground(Color.BLACK);
		connectionTextField.setBackground(Color.LIGHT_GRAY);
		connectionTextField.setText("Not Connected...");
		connectionTextField.setBounds(168, 149, 204, 23);
		frmPasswordManager.getContentPane().add(connectionTextField);
		connectionTextField.setColumns(10);
		
		addUsernameTextField = new JTextField();
		addUsernameTextField.setBackground(Color.LIGHT_GRAY);
		addUsernameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addUsernameTextField.setBounds(168, 305, 204, 20);
		frmPasswordManager.getContentPane().add(addUsernameTextField);
		addUsernameTextField.setVisible(false);
		addUsernameTextField.setColumns(10);
		
		JLabel addUsernameLabel = new JLabel("Add username:");
		addUsernameLabel.setForeground(Color.LIGHT_GRAY);
		addUsernameLabel.setBackground(Color.YELLOW);
		addUsernameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addUsernameLabel.setBounds(10, 308, 129, 14);
		addUsernameLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(addUsernameLabel);
		
		JLabel addPasswordLabel = new JLabel("Add password:");
		addPasswordLabel.setForeground(Color.LIGHT_GRAY);
		addPasswordLabel.setBackground(Color.YELLOW);
		addPasswordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addPasswordLabel.setBounds(10, 342, 129, 14);
		addPasswordLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(addPasswordLabel);
		
		addPasswordTextField = new JTextField();
		addPasswordTextField.setBackground(Color.LIGHT_GRAY);
		addPasswordTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addPasswordTextField.setBounds(168, 339, 204, 20);
		frmPasswordManager.getContentPane().add(addPasswordTextField);
		addPasswordTextField.setVisible(false);
		addPasswordTextField.setColumns(10);
		
		JLabel findUsernameLabel = new JLabel("Find username:");
		findUsernameLabel.setBackground(Color.YELLOW);
		findUsernameLabel.setForeground(Color.LIGHT_GRAY);
		findUsernameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findUsernameLabel.setBounds(10, 308, 129, 14);
		findUsernameLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(findUsernameLabel);
		
		findUsernameTextField = new JTextField();
		findUsernameTextField.setBackground(Color.LIGHT_GRAY);
		findUsernameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findUsernameTextField.setBounds(168, 305, 204, 20);
		frmPasswordManager.getContentPane().add(findUsernameTextField);
		findUsernameTextField.setVisible(false);
		findUsernameTextField.setColumns(10);
		
		JLabel findPasswordLabel = new JLabel("Find password:");
		findPasswordLabel.setForeground(Color.LIGHT_GRAY);
		findPasswordLabel.setBackground(Color.WHITE);
		findPasswordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findPasswordLabel.setBounds(10, 342, 129, 14);
		findPasswordLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(findPasswordLabel);
		
		findPasswordTextField = new JTextField();
		findPasswordTextField.setBackground(Color.LIGHT_GRAY);
		findPasswordTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findPasswordTextField.setBounds(168, 339, 204, 20);
		frmPasswordManager.getContentPane().add(findPasswordTextField);
		findPasswordTextField.setVisible(false);
		findPasswordTextField.setColumns(10);
		
		JButton addPasswordButton = new JButton("Add password");
		addPasswordButton.setForeground(Color.BLACK);
		addPasswordButton.setBackground(Color.LIGHT_GRAY);
		addPasswordButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addPasswordButton.setBounds(10, 217, 120, 23);
		frmPasswordManager.getContentPane().add(addPasswordButton);
		
		JButton findPasswordButton = new JButton("Find password");
		findPasswordButton.setForeground(Color.BLACK);
		findPasswordButton.setBackground(Color.LIGHT_GRAY);
		findPasswordButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findPasswordButton.setBounds(168, 217, 117, 23);
		frmPasswordManager.getContentPane().add(findPasswordButton);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setForeground(Color.BLACK);
		connectButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		connectButton.setBackground(Color.LIGHT_GRAY);
		connectButton.setBounds(10, 149, 120, 23);
		frmPasswordManager.getContentPane().add(connectButton);
		
		JButton enterButton = new JButton("Enter");
		enterButton.setForeground(Color.BLACK);
		enterButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		enterButton.setBackground(Color.LIGHT_GRAY);
		enterButton.setBounds(168, 397, 89, 23);
		frmPasswordManager.getContentPane().add(enterButton);
		
		addServiceTextField = new JTextField();
		addServiceTextField.setToolTipText("Must be filled in!");
		addServiceTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addServiceTextField.setBackground(Color.LIGHT_GRAY);
		addServiceTextField.setBounds(168, 270, 204, 20);
		frmPasswordManager.getContentPane().add(addServiceTextField);
		addServiceTextField.setVisible(false);
		addServiceTextField.setColumns(10);
		
		JLabel findServiceLabel = new JLabel("Find service:");
		findServiceLabel.setForeground(Color.LIGHT_GRAY);
		findServiceLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findServiceLabel.setBounds(10, 273, 89, 14);
		findServiceLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(findServiceLabel);
		
		findServiceTextField = new JTextField();
		findServiceTextField.setToolTipText("");
		findServiceTextField.setBackground(Color.LIGHT_GRAY);
		findServiceTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		findServiceTextField.setBounds(168, 270, 201, 20);
		frmPasswordManager.getContentPane().add(findServiceTextField);
		findServiceTextField.setVisible(false);
		findServiceTextField.setColumns(10);
		
		JLabel addServiceLabel = new JLabel("*Add service:");
		addServiceLabel.setToolTipText("Must be filled in!");
		addServiceLabel.setForeground(Color.LIGHT_GRAY);
		addServiceLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		addServiceLabel.setBackground(Color.LIGHT_GRAY);
		addServiceLabel.setBounds(10, 273, 89, 14);
		addServiceLabel.setVisible(false);
		frmPasswordManager.getContentPane().add(addServiceLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(454, 71, 350, 349);
		frmPasswordManager.getContentPane().add(scrollPane);
		
		resultTable = new JTable();
		resultTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		scrollPane.setViewportView(resultTable);
		resultTable.setFillsViewportHeight(true);
		resultTable.setBackground(Color.LIGHT_GRAY);
		
		JButton customButton = new JButton("Custom input");
		customButton.setForeground(Color.BLACK);
		customButton.setBackground(Color.LIGHT_GRAY);
		customButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		customButton.setBounds(305, 217, 120, 23);
		frmPasswordManager.getContentPane().add(customButton);
		
		JTextArea customTextArea = new JTextArea();
		customTextArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		customTextArea.setBackground(Color.LIGHT_GRAY);
		customTextArea.setBounds(10, 269, 415, 117);
		customTextArea.setVisible(false);
		frmPasswordManager.getContentPane().add(customTextArea);
		
		JLabel tipLabel = new JLabel("* = Item must be filled in");
		tipLabel.setForeground(Color.LIGHT_GRAY);
		tipLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		tipLabel.setBackground(Color.LIGHT_GRAY);
		tipLabel.setBounds(10, 428, 190, 14);
		frmPasswordManager.getContentPane().add(tipLabel);
		
		
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = new String(passwordTextField.getPassword());
				
				try {

					if (connectedToDatabase == true) {
						connection.close();

						connectionTextField.setText("No Connection Now");
						connectionTextField.setForeground(Color.black);
						connectionTextField.setBackground(Color.LIGHT_GRAY);

						connectedToDatabase = false;
						connectButton.setText("Connect");

					}

					else {
						Class.forName("com.mysql.cj.jdbc.Driver");

						connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager?useTimezone=true&serverTimezone=UTC", usernameTextField.getText(), password);

						connectionTextField.setText("Connected to Database");
						connectionTextField.setBackground(Color.black);
						connectionTextField.setForeground(Color.LIGHT_GRAY);

						connectedToDatabase = true;
						
						connectButton.setText("Disconnect");
					}
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		addPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPasswordLabel.setVisible(false);
				findPasswordTextField.setVisible(false);
				findUsernameLabel.setVisible(false);
				findUsernameTextField.setVisible(false);
				findServiceTextField.setVisible(false);
				findServiceLabel.setVisible(false);
				customTextArea.setVisible(false);
				addPasswordLabel.setVisible(true);
				addPasswordTextField.setVisible(true);
				addUsernameLabel.setVisible(true);
				addUsernameTextField.setVisible(true);
				addServiceTextField.setVisible(true);
				addServiceLabel.setVisible(true);
				
				findUsernameTextField.setText(null);
				findPasswordTextField.setText(null);
				findServiceTextField.setText(null);
				customTextArea.setText(null);

				option = 0;
			}
		});
		
		findPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPasswordLabel.setVisible(false);
				addPasswordTextField.setVisible(false);
				addUsernameLabel.setVisible(false);
				addUsernameTextField.setVisible(false);
				addServiceTextField.setVisible(false);
				addServiceLabel.setVisible(false);
				customTextArea.setVisible(false);
				findPasswordLabel.setVisible(true);
				findPasswordTextField.setVisible(true);
				findUsernameLabel.setVisible(true);
				findUsernameTextField.setVisible(true);
				findServiceTextField.setVisible(true);
				findServiceLabel.setVisible(true);
				
				addPasswordTextField.setText(null);
				addUsernameTextField.setText(null);
				addServiceTextField.setText(null);
				customTextArea.setText(null);
				
				option = 1;
			}
		});
		
		customButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPasswordLabel.setVisible(false);
				addPasswordTextField.setVisible(false);
				addUsernameLabel.setVisible(false);
				addUsernameTextField.setVisible(false);
				addServiceTextField.setVisible(false);
				addServiceLabel.setVisible(false);
				findPasswordLabel.setVisible(false);
				findPasswordTextField.setVisible(false);
				findUsernameLabel.setVisible(false);
				findUsernameTextField.setVisible(false);
				findServiceTextField.setVisible(false);
				findServiceLabel.setVisible(false);
				customTextArea.setVisible(true);
				
				addPasswordTextField.setText(null);
				addUsernameTextField.setText(null);
				addServiceTextField.setText(null);
				findUsernameTextField.setText(null);
				findPasswordTextField.setText(null);
				findServiceTextField.setText(null);
				
				option = 2;
			}
		});
		
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = new String(passwordTextField.getPassword());
				
				String query0 = "insert into accounts (service, usernames, passwords) values ('" + addServiceTextField.getText() + "', '" + addUsernameTextField.getText() + "', '" + addPasswordTextField.getText() + "');";
				String query1 = "SELECT * FROM accounts WHERE"  
									+ "(usernames LIKE '" + findUsernameTextField.getText() + "%')"  
									+ "OR (usernames LIKE '%" + findUsernameTextField.getText() + "')" 
									+ "OR (usernames LIKE '%" + findUsernameTextField.getText() + "%')" 
									+ "OR (usernames = '" + findUsernameTextField.getText() + "')" 
									+ "OR (passwords LIKE '" + findPasswordTextField.getText() + "%')"  
									+ "OR (passwords LIKE '%" + findPasswordTextField.getText() + "')" 
									+ "OR (passwords LIKE '%" + findPasswordTextField.getText() + "%')" 
									+ "OR (passwords = '" + findPasswordTextField.getText() + "')"
									+ "OR (service LIKE '%" + findServiceTextField.getText() + "')" 
									+ "OR (service LIKE '" + findServiceTextField.getText() + "%')" 
									+ "OR (service LIKE '%" + findServiceTextField.getText() + "%')" 
									+ "OR (service LIKE '" + findServiceTextField.getText() + "');";
				String query2 = customTextArea.getText();
	
				try {
					tableModel = new ResultSetTableModel(option, query0, query1, query2, "jdbc:mysql://localhost:3306/passwordmanager?useTimezone=true&serverTimezone=UTC", usernameTextField.getText(), password);
				} catch (ClassNotFoundException | SQLException e1) {					 
					JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				}
				
				resultTable.setModel(tableModel);
	
				try {
					if (connectedToDatabase == true && tableModel != null) {
						System.out.println("Are we here???");

						
						if (option == 1) {
							tableModel.setQuery(query1);
							System.out.println("Did we make it yet?");
						}
												
						else if (option == 0) {
							PreparedStatement statement = connection.prepareStatement(query0);
								
							if (statement.executeUpdate() > 0) {
								resultTable.setModel(new DefaultTableModel());
								System.out.println("interesting...");
							}
						}
						
						else if (query2.toLowerCase().startsWith("s")) {
							tableModel.setQuery(query2);
							System.out.println("yoooooo");
						}
						
						else {
							PreparedStatement statement = connection.prepareStatement(query2);
							
							if (statement.executeUpdate() > 0) {
								resultTable.setModel(new DefaultTableModel());
								System.out.println("lezzz gooooooo!");
							}
						}
						
					}
				} catch (IllegalStateException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
}