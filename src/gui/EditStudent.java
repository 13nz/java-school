package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.Grade;
import models.Student;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent frame = new EditStudent();
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
	public EditStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public EditStudent(int id) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Student student = new Student(id);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 11, 100, 14);
		contentPane.add(lblNewLabel);
		
		JTextField fname = new JTextField(student.getFname());
		fname.setBounds(10, 27, 100, 24);
		contentPane.add(fname);
		
		JLabel lblNewLabel_2 = new JLabel("Last name:");
		lblNewLabel_2.setBounds(10, 62, 100, 14);
		contentPane.add(lblNewLabel_2);
		
		JTextField lname = new JTextField(student.getLname());
		lname.setBounds(10, 80, 106, 26);
		contentPane.add(lname);
		
		JLabel lblNewLabel_4 = new JLabel("Birth date:");
		lblNewLabel_4.setBounds(10, 117, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		JTextField bdate = new JTextField(student.getBdate());
		bdate.setBounds(10, 129, 106, 26);
		contentPane.add(bdate);
		
		JLabel varaddress = new JLabel("Address");
		varaddress.setBounds(10, 166, 100, 14);
		contentPane.add(varaddress);
		
		JTextField address = new JTextField(student.getAddress());
		address.setBounds(10, 184, 100, 45);
		contentPane.add(address);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentProfile profile = new StudentProfile(id);
					hide();
					profile.show();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBack.setBounds(0, 238, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					student.update("fname='" + fname.getText() + "', lname='" + lname.getText() + "', bdate='" + bdate.getText() + "', address='" + address.getText() + "'");
					
					StudentProfile profile = new StudentProfile(student.getId());
					hide();
					profile.show();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(97, 238, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Yes", "Cancel"};
				int n = JOptionPane.showConfirmDialog(
					    null,
					    "Are you sure?",
					    "Warning",
					    JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					try {
						student.delete();
						AllStudents students = new AllStudents();
						hide();
						students.show();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnDelete.setBounds(196, 238, 89, 23);
		contentPane.add(btnDelete);
	}
}
