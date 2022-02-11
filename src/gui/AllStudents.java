package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import models.Student;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AllStudents extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllStudents frame = new AllStudents();
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
	public AllStudents() {
		setTitle("Students");
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
		
		JButton btnNewStudent = new JButton("New Student");
		btnNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewStudent newStudent = new NewStudent();
				hide();
				newStudent.show();
			}
		});
		btnNewStudent.setBounds(345, 0, 89, 23);
		contentPane.add(btnNewStudent);
		
		JList studentList = new JList();
		studentList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Student student = (Student) studentList.getSelectedValue();
					StudentProfile profile;
					try {
						profile = new StudentProfile(student.getId());
						hide();
						profile.show();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		studentList.setBackground(SystemColor.controlHighlight);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final ArrayList<Student> students;
		DefaultListModel model = new DefaultListModel();
		try {
			students = Student.getAll();
			for (int i = 0; i < students.size(); i++) {
				model.addElement(students.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentList.setModel(model);
		contentPane.add(studentList);
		studentList.setBounds(88, 63, 252, 187);
		
		
		
		
	}
}
