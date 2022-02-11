package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student {
	
	private int id;
	private String fname;
	private String lname;
	private String bdate;
	private int gender;
	private String address;
	private Connection conn = SqlUtils.getConnection();
	
	


	public Student(String fname, String lname, String bdate, int gender, String address) {
		this.fname = fname;
		this.lname = lname;
		this.bdate = bdate;
		this.gender = gender;
		this.address = address;
	}
	
	
	public Student(int id) throws SQLException {
		String sql = "SELECT * FROM students WHERE id = '" + id + "'";
		
		
	
		ResultSet resultSet = SqlUtils.selectFromDB(sql);  
		
		
		resultSet.first();
		
		this.id = resultSet.getInt("id");
		this.fname = resultSet.getString("fname");
		this.lname = resultSet.getString("lname");
		this.bdate = resultSet.getString("bdate");
		this.gender = Integer.parseInt(resultSet.getString("gender"));
		this.address = resultSet.getString("address");
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getBdate() {
		return bdate;
	}


	public void setBdate(String bdate) {
		this.bdate = bdate;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFullName() {
		return this.getFname() + " " + this.getLname();
	}
	
	public static String getStudentFName(int id) throws SQLException {
		Student student = new Student(id);
		
		return student.getFname();
	}
	
	public static String getStudentLName(int id) throws SQLException {
		Student student = new Student(id);
		
		return student.getLname();
	}
	
	
	public void save() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (fname, lname, bdate, gender, address) VALUES (?, ?, ?, ?, ?)");
		
		stmt.setString(1, this.fname);
		stmt.setString(2, this.lname);
		stmt.setString(3,  this.bdate);
		stmt.setInt(4, this.gender);
		stmt.setString(5, this.address);
		
	
		SqlUtils.save(stmt);
		
	}
	
	public static ArrayList<Student> getAll() throws SQLException {
		ArrayList<Student> students = new ArrayList<Student>();
		
		String query = "SELECT * FROM students";
	
		ResultSet resultSet = SqlUtils.selectFromDB(query);
		resultSet.first();
		
		while (!resultSet.isAfterLast()) {
			Student student = new Student(resultSet.getInt("id"));
			students.add(student);
			resultSet.next();
		}
		
		return students;
	}
	
	 public ArrayList<Grade> getGrades() throws SQLException {
		 String sql = "SELECT * FROM grades WHERE student_id = '" + this.id + "'";
		 ArrayList<Grade> grades = new ArrayList<Grade>();
		 
		 ResultSet resultSet = SqlUtils.selectFromDB(sql);
		 resultSet.first();
		 
		 while (!resultSet.isAfterLast() ) {
			 Grade grade = new Grade(resultSet.getInt("id"));
			 grades.add(grade);
			 resultSet.next();
		 }
		 
		 return grades;
		 
	 }
	 
	 public ArrayList<Course> getCourses() throws SQLException {
		 String sql = "SELECT * FROM grades WHERE student_id = '" + this.id + "'";
		 
		 ArrayList<Course> courses = new ArrayList<Course>();
		 
		 
		 ResultSet resultSet = SqlUtils.selectFromDB(sql);
		 resultSet.first();
		 
		 while (!resultSet.isAfterLast() ) {
			 Course course = new Course(resultSet.getInt("course_id"));
			 courses.add(course);
			 resultSet.next();
		 }
		 
		 return courses;
		 
	 }
	 
	 
	 public void enroll(int course_id) throws SQLException {
		 PreparedStatement stmt = conn.prepareStatement("INSERT INTO grades (student_id, course_id) VALUES (?, ?)");
		 
		 stmt.setInt(1, this.id);
		 stmt.setInt(2, course_id);
		 
		 SqlUtils.save(stmt);
	 }
	 
	 public void enroll(Course course) throws SQLException {
		 PreparedStatement stmt = conn.prepareStatement("INSERT INTO grades (student_id, course_id) VALUES (?, ?)");
		 
		 int course_id = course.getId();
		 
		 stmt.setInt(1, this.id);
		 stmt.setInt(2, course_id);
		 
		 SqlUtils.save(stmt);
	 }
	 
	 
	 public void delete() throws SQLException {
		 SqlUtils.deleteForeign("grades", "student_id", this.id);
		 SqlUtils.delete("students", this.id);
	 }
	 
	 public void update(String values) throws SQLException {
		 SqlUtils.update("students", this.id, values);
	 }

	@Override
	public String toString() {
		return fname + " " + lname;
	}
	
	 
	
}
