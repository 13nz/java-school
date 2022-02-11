package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Course;
import models.SqlUtils;
import models.Student;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JList list;
	private JButton btnSearch;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
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
	public Search() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(20, 20, 174, 22);
		contentPane.add(txt);
		txt.setColumns(10);
		
		JComboBox select = new JComboBox();
		select.addItem("Students");
		select.addItem("Courses");
		select.setBounds(215, 20, 192, 22);
		contentPane.add(select);
		
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = (String) select.getSelectedItem();
				String search = txt.getText();
				
				if (selection.equals("Students")) {
					try {
						ResultSet rs = SqlUtils.selectFromDB("SELECT * FROM students WHERE fname LIKE '%" + search + "%' OR lname LIKE '%" + search + "%'");
						rs.first();
						ArrayList<Student> students = new ArrayList<Student>();
						while (!rs.isAfterLast()) {
							Student student = new Student(rs.getInt("id"));
							students.add(student);
							rs.next();
						}
						DefaultListModel model = new DefaultListModel();
						for (int i = 0; i < students.size(); i++) {
							model.addElement(students.get(i));
						}
						list.setModel(model);
						
						list.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getClickCount() == 2) {
									Student student = (Student) list.getSelectedValue();
									StudentProfile profile;
									try {
										profile = new StudentProfile(student.getId());
										profile.show();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								
							}
						});
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				} else if (selection.equals("Courses")) {
					try {
						ResultSet rs = SqlUtils.selectFromDB("SELECT * FROM courses WHERE name LIKE '%" + search + "%'");
						rs.first();
						ArrayList<Course> courses = new ArrayList<Course>();
						while (!rs.isAfterLast()) {
							Course course = new Course(rs.getInt("id"));
							courses.add(course);
							rs.next();
						}
						
						DefaultListModel model = new DefaultListModel();
						for (int i = 0; i < courses.size(); i++) {
							model.addElement(courses.get(i));
						}
						list.setModel(model);
						
						list.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getClickCount() == 2) {
									Course course = (Course) list.getSelectedValue();
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
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		});
		
		

		list = new JList();
		
		list.setBounds(80, 103, 260, 109);
		contentPane.add(list);
		
		btnSearch.setBounds(158, 69, 89, 23);
		contentPane.add(btnSearch);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnBack.setBounds(158, 223, 89, 23);
		contentPane.add(btnBack);
	}
}
