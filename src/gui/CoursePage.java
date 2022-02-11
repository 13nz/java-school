package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Course;
import models.Grade;
import models.Student;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CoursePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursePage frame = new CoursePage();
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
	
	public CoursePage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	
	
	
	public CoursePage(int id) throws SQLException {
		Course course = new Course(id);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course:");
		lblNewLabel.setBounds(10, 11, 113, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCourseName = new JLabel(course.getName());
		lblCourseName.setBounds(10, 36, 113, 14);
		contentPane.add(lblCourseName);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
		
		JList studentList = new JList();
		studentList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Grade grade = (Grade) studentList.getSelectedValue();
					String s = JOptionPane.showInputDialog("Add grade");
					try {
						grade.insert(Integer.parseInt(s));
						CoursePage coursePage = new CoursePage(grade.getCourse_id());
						hide();
						coursePage.show();
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		final ArrayList<Grade> grades;
		DefaultListModel model = new DefaultListModel();
		try {
			grades = course.getGrades();
			for (int i = 0; i < grades.size(); i++) {
				model.addElement(grades.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		studentList.setModel(model);
		studentList.setBounds(241, 34, 183, 204);
		contentPane.add(studentList);
		
		JLabel lblNewLabel_2 = new JLabel("Grades:");
		lblNewLabel_2.setBounds(241, 11, 113, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnedit = new JButton("Edit");
		btnedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCourse edit;
				try {
					edit = new EditCourse(course.getId());
					hide();
					edit.show();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnedit.setBounds(114, 227, 89, 23);
		contentPane.add(btnedit);

	}
}
