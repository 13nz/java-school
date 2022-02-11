package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Student;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;

public class NewStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txtFName;
	private JTextField txtLName;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStudent frame = new NewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewStudent() {
		setTitle("New Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setBounds(10, 35, 75, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setBounds(10, 60, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Birth date");
		lblNewLabel_2.setBounds(10, 85, 75, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(10, 110, 75, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 135, 75, 14);
		contentPane.add(lblNewLabel_4);
		
		txtFName = new JTextField();
		txtFName.setBounds(177, 32, 165, 20);
		contentPane.add(txtFName);
		txtFName.setColumns(10);
		
		txtLName = new JTextField();
		txtLName.setBounds(177, 57, 165, 20);
		contentPane.add(txtLName);
		txtLName.setColumns(10);
		
		JRadioButton rdFemale = new JRadioButton("Female");
		buttonGroup.add(rdFemale);
		rdFemale.setBounds(177, 106, 90, 23);
		contentPane.add(rdFemale);
		
		JRadioButton rdMale = new JRadioButton("Male");
		buttonGroup.add(rdMale);
		rdMale.setBounds(265, 106, 106, 23);
		contentPane.add(rdMale);
		
		JTextArea textAreaAddress = new JTextArea();
		textAreaAddress.setBounds(177, 135, 165, 75);
		contentPane.add(textAreaAddress);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(177, 85, 165, 20);
		contentPane.add(dateChooser);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int gender = 0;
				if (rdFemale.isSelected()) {
					gender = 1;
				} else if (rdMale.isSelected()) {
					gender = 2;
				}
				
				String bdate = String.format("%1$tY-%1$tm-%1$td", dateChooser.getDate());
				
				Student student = new Student(txtFName.getText(), txtLName.getText(), bdate , gender, textAreaAddress.getText());
				try {
					student.save();
					AllStudents students = new AllStudents();
					hide();
					students.show();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hide();
			}
		});
		btnSave.setBounds(213, 227, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
				
			}
		});
		btnBack.setBounds(0, 0, 75, 20);
		contentPane.add(btnBack);
		
		
	}
}
