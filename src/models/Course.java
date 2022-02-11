package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
	
	private int id;
	private String name;
	private Connection conn = SqlUtils.getConnection();
	
	
	public Course(String name) {
		this.name = name;
	}
	
	public Course(int id) throws SQLException {
		String sql = "SELECT * FROM courses WHERE id = '" + id + "'";
		
		ResultSet resultSet = SqlUtils.selectFromDB(sql);
		
		
		resultSet.first();
		
		this.id = resultSet.getInt("id");
		this.name = resultSet.getString("name");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public static String getCourseName(int id) throws SQLException {
		Course course = new Course(id);
		
		return course.getName();
	}
	
	
	public void save() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses (name) VALUES (?)");
		
		stmt.setString(1, this.name);
		SqlUtils.save(stmt);
	}
	
	public void delete() throws SQLException {
		SqlUtils.deleteForeign("grades", "course_id", this.id);
		SqlUtils.delete("courses", this.id);
	}
	
	public void update(String values) throws SQLException {
		SqlUtils.update("courses", this.id, values);
	}
	
	public static ArrayList<Course> getAll() throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		String query = "SELECT * FROM courses";
	
		ResultSet resultSet = SqlUtils.selectFromDB(query);
		resultSet.first();
		
		while (!resultSet.isAfterLast()) {
			Course course = new Course(resultSet.getInt("id"));
			courses.add(course);
			resultSet.next();
		}
		
		return courses;
	}
	
	public ArrayList<Student> getStudents() throws SQLException {
		String sql = "SELECT * FROM grades WHERE course_id = '" + this.id + "'";
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		ResultSet rs = SqlUtils.selectFromDB(sql);
		rs.first();
		
		while (!rs.isAfterLast()) {
			Student student = new Student(rs.getInt("student_id"));
			students.add(student);
			rs.next();
		}
		
		return students;
	}
	
	public ArrayList<Grade> getGrades() throws SQLException {
		String sql = "SELECT * FROM grades WHERE course_id = '" + this.id + "'";
		
		ArrayList<Grade> grades = new ArrayList<Grade>();
		
		
		ResultSet rs = SqlUtils.selectFromDB(sql);
		rs.first();
		
		while (!rs.isAfterLast()) {
			Grade grade = new Grade(rs.getInt("id"));
			grades.add(grade);
			rs.next();
		}
		
		return grades;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
