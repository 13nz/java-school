package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Course;
import models.Grade;
import models.SqlUtils;
import models.Student;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentProfile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentProfile frame = new StudentProfile();
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
	
	public StudentProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public StudentProfile(int id) throws SQLException {
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
		
		JLabel fname = new JLabel(student.getFname());
		fname.setBounds(10, 27, 100, 14);
		contentPane.add(fname);
		
		JLabel lblNewLabel_2 = new JLabel("Last name:");
		lblNewLabel_2.setBounds(10, 62, 100, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lname = new JLabel(student.getLname());
		lname.setBounds(10, 80, 100, 14);
		contentPane.add(lname);
		
		JLabel lblNewLabel_4 = new JLabel("Birth date:");
		lblNewLabel_4.setBounds(10, 117, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel bdate = new JLabel(student.getBdate());
		bdate.setBounds(10, 129, 100, 14);
		contentPane.add(bdate);
		
		JLabel varaddress = new JLabel("Address");
		varaddress.setBounds(10, 166, 100, 14);
		contentPane.add(varaddress);
		
		JLabel address = new JLabel(student.getAddress());
		address.setVerticalAlignment(SwingConstants.TOP);
		address.setBounds(10, 184, 100, 54);
		contentPane.add(address);
		
		JLabel lblNewLabel_1 = new JLabel("Grades");
		lblNewLabel_1.setBounds(255, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JList gradesList = new JList();
		
		
		final ArrayList<Grade> grades;
		DefaultListModel model = new DefaultListModel();
		try {
			grades = student.getGrades();
			for (int i = 0; i < grades.size(); i++) {
				model.addElement(grades.get(i).getCourseName() + ": " + grades.get(i).getGrade());
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gradesList.setModel(model);
		
		gradesList.setBounds(255, 36, 169, 172);
		contentPane.add(gradesList);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnBack.setBounds(0, 238, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditStudent edit = new EditStudent(id);
					hide();
					edit.show();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEdit.setBounds(97, 238, 89, 23);
		contentPane.add(btnEdit);
		
		JLabel lblNewLabel_3 = new JLabel("Average: ");
		lblNewLabel_3.setBounds(255, 219, 62, 14);
		contentPane.add(lblNewLabel_3);
		
		
		double avg = SqlUtils.average(student.getId());
		
		
		JLabel average = new JLabel("" + avg + "");
		average.setBounds(339, 219, 46, 14);
		contentPane.add(average);
		
	}
}
