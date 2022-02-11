package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {

	private static final String url = "jdbc:mysql://localhost:3306/school_db";
	private static final String user = "root";
	private static final String password = "";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getStatement() throws SQLException {
		Connection connection = getConnection();
		return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
	}
	
	public static ResultSet selectFromDB(String sql) throws SQLException {
		Statement statement = SqlUtils.getStatement();
	
		ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(sql);
		
		return resultSet;
	}
	
	public static void save(PreparedStatement stmt) throws SQLException {
		stmt.execute();
	}
	
	public static void delete(String table, int id) throws SQLException {
		Statement statement = getStatement();
		
		statement.execute("DELETE FROM " + table + " WHERE id='" + id + "'");
	}
	
	public static void deleteForeign(String table, String column, int id) throws SQLException {
		Statement statement = getStatement();
		
		statement.execute("DELETE FROM " + table + " WHERE " + column + "='" + id + "'");
	}
	
	public static void update(String table, int id, String values) throws SQLException {
		Statement statement = getStatement();
		
		statement.execute("UPDATE " + table + " SET " + values + " WHERE id='" + id + "'");
	}
	
	public static double average(int id) throws SQLException {
		Statement statement = getStatement();
		
		ResultSet rs = statement.executeQuery("SELECT AVG(grade) FROM grades WHERE student_id='" + id + "' AND grade!='0'");
		rs.first();
		return rs.getDouble(1);
	}
	
}
