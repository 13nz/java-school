package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Course;
import models.Student;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Enroll extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enroll frame = new Enroll();
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
	public Enroll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student");
		lblNewLabel.setBounds(28, 44, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course");
		lblNewLabel_1.setBounds(237, 44, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBoxStudent = new JComboBox();
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			students = Student.getAll();
			for (int i = 0; i < students.size(); i++) {
				comboBoxStudent.addItem(students.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxStudent.setBounds(28, 69, 138, 22);
		contentPane.add(comboBoxStudent);
		
		JComboBox comboBoxCourse = new JComboBox();
		ArrayList<Course> courses = new ArrayList<Course>();
		
		try {
			courses = Course.getAll();
			for (int i = 0; i < courses.size(); i++) {
				comboBoxCourse.addItem(courses.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxCourse.setBounds(231, 69, 138, 22);
		contentPane.add(comboBoxCourse);
		
		JButton btnNewButton = new JButton("Enroll");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = (Student) comboBoxStudent.getSelectedItem();
				int student_id = student.getId();
				
				Course course = (Course) comboBoxCourse.getSelectedItem();
				int course_id = course.getId();
				
				try {
					student.enroll(course_id);
					hide();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(149, 182, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnCancel.setBounds(149, 216, 89, 23);
		contentPane.add(btnCancel);
	}
}
