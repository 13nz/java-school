package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frmHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 450, 300);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		JButton btnStudents = new JButton("Students");
		btnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllStudents students = new AllStudents();
				students.show();
			}
		});
		btnStudents.setBounds(163, 54, 89, 23);
		frmHome.getContentPane().add(btnStudents);
		
		JButton btnCourses = new JButton("Courses");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllCourses courses = new AllCourses();
				courses.show();
			}
		});
		btnCourses.setBounds(163, 99, 89, 23);
		frmHome.getContentPane().add(btnCourses);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enroll enroll = new Enroll();
				enroll.show();
			}
		});
		btnEnroll.setBounds(163, 146, 89, 23);
		frmHome.getContentPane().add(btnEnroll);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				search.show();
			}
		});
		btnSearch.setBounds(163, 192, 89, 23);
		frmHome.getContentPane().add(btnSearch);
	}
}
