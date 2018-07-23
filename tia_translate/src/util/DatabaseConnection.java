package util;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	// SQLServer
//	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ca";
//	private static final String ID = "sa";
//	private static final String PASS = "sa";
	
	// Oracle
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String DB_URL = "jdbc:oracle:thin:@dbserver:1521:xe";
//	private static final String ID = "sys";
//	private static final String PASS = "orcl";
	
	// MySQL
//	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
//	private static final String DB_URL = "jdbc:mysql://localhost/ca";
//	private static final String ID = "root";
//	private static final String PASS = "";
	
	// PostgreSQL
//	private static final String DRIVER_NAME = "org.postgresql.Driver";
//	private static final String DB_URL = "jdbc:postgresql:ca";
//	private static final String ID = "postgres";
//	private static final String PASS = "postgres";

	// SQLite
	private static final String DRIVER_NAME = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:Data.db";
	
	
	

	
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(DB_URL);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void close(Connection con) throws SQLException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw e;
			}
		}
	}
}
