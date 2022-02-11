package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Grade {

	
		private int id;
		private int student_id;
		private int course_id;
		private int grade;
		private String studentFName;
		private String studentLName;
		private String courseName;
		private Connection conn = SqlUtils.getConnection();
		
		
		public Grade(int student_id, int course_id, int grade) {
			super();
			this.student_id = student_id;
			this.course_id = course_id;
			this.grade = grade;
		}
		
		public Grade(int id) throws SQLException {
			String sql = "SELECT * FROM grades WHERE id = '" + id + "'";
			
			ResultSet resultSet = SqlUtils.selectFromDB(sql);
			
			
			resultSet.first();
			
			this.id = id;
			this.student_id = resultSet.getInt("student_id");
			this.course_id = resultSet.getInt("course_id");
			this.grade = resultSet.getInt("grade");
			this.setStudentFName(Student.getStudentFName(student_id));
			this.setStudentLName(Student.getStudentLName(student_id));
			this.setCourseName(Course.getCourseName(course_id));
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public int getStudent_id() {
			return student_id;
		}


		public void setStudent_id(int student_id) {
			this.student_id = student_id;
		}


		public int getCourse_id() {
			return course_id;
		}


		public void setCourse_id(int course_id) {
			this.course_id = course_id;
		}


		public int getGrade() {
			return grade;
		}


		public void setGrade(int grade) {
			this.grade = grade;
		}
		
		public String getStudentFName() {
			return studentFName;
		}

		public void setStudentFName(String studentFName) {
			this.studentFName = studentFName;
		}
		
		public String getStudentLName() {
			return studentLName;
		}

		public void setStudentLName(String studentLName) {
			this.studentLName = studentLName;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		
		
		public void save() throws SQLException {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)");
			
			stmt.setInt(1, this.student_id);
			stmt.setInt(2, course_id);
			stmt.setInt(3, grade);
			
			
			SqlUtils.save(stmt);
		}
		
		public void delete() throws SQLException {
			SqlUtils.delete("grades", this.id);
		}
		
		public void update(String values) throws SQLException {
			SqlUtils.update("grades", this.id, values);
		}
		
		public void insert(int grade) throws SQLException {
			PreparedStatement stmt = conn.prepareStatement("UPDATE grades SET grade = ? WHERE student_id = ? AND course_id = ?");
			stmt.setInt(1, grade);
			stmt.setInt(2, this.student_id);
			stmt.setInt(3, this.course_id);
			
			stmt.execute();
		}

		@Override
		public String toString() {
			return this.studentFName + " " + this.studentLName + ": " + this.grade;
		}

		
		
}





