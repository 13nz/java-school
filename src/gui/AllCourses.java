package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import models.Course;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AllCourses extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCourses frame = new AllCourses();
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
	public AllCourses() {
		setTitle("Courses");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnHome.setBounds(0, 0, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
				AddCourse addCourse = new AddCourse();
				addCourse.show();
			}
		});
		btnAddCourse.setBounds(345, 0, 89, 23);
		contentPane.add(btnAddCourse);
		
		JList coursesList = new JList();
		coursesList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Course course = (Course) coursesList.getSelectedValue();
					try {
						CoursePage coursePage = new CoursePage(course.getId());
						
						coursePage.show();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		coursesList.setBackground(SystemColor.controlHighlight);
		coursesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final ArrayList<Course> courses;
		DefaultListModel model = new DefaultListModel();
		
		try {
			courses = Course.getAll();
			for (int i = 0; i < courses.size(); i++) {
				model.addElement(courses.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		coursesList.setModel(model);
		coursesList.setBounds(88, 67, 254, 168);
		contentPane.add(coursesList);
	}

}
