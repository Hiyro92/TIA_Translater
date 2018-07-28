package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.ProjectHead;
import util.DatabaseConnection;

public class ProjectHeadDAO {
	final private Logger LOGGER = LogManager.getLogger();
	
	
	private static final String DELETE = "DELETE FROM PROJECT_HEAD WHERE id=?";
	private static final String DROP_TABLE = "DROP TABLE ?";
	private static final String FIND_ALL = "SELECT * FROM PROJECT_HEAD";
	private static final String GET_TABLE_LENGHT = "SELECT COUNT(*) FROM ?";
	private static final String FIND_BY_ID = "SELECT * FROM PROJECT_HEAD WHERE id=?";
	private static final String INSERT = "INSERT INTO PROJECT_HEAD (NAME, DATE, TABLE_NAME) VALUES(?, ?, ?)";
	private static final String CREATE_TABLE = "CREATE TABLE ? (ID INT PRIMARY KEY NOT NULL)";

	
	public void add(ProjectHead data){
		Connection c = null;
		PreparedStatement ps = null;
		Statement s  = null;
		try {
			SimpleDateFormat ft = new SimpleDateFormat ("HHssSSS");
			String tableName = data.getName().toUpperCase() + ft.format(data.getCreatingDate());
			tableName = tableName.replace(" ", "");
			
			c = DatabaseConnection.getConnection();
			ps = c.prepareStatement(INSERT);
			ps.setString(1, data.getName());
			ps.setLong(2, data.getCreatingDate().getTime());	
			ps.setString(3, tableName);
			ps.executeUpdate();			
			
			System.out.println(tableName);
			
			s = c.createStatement();
			s.executeUpdate(CREATE_TABLE.replace("?", tableName));
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			try {
				DatabaseConnection.close(ps);
				DatabaseConnection.close(s);
				DatabaseConnection.close(c);
				
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
			
		}
	}
	
	public List<ProjectHead> getAll(){
		Connection c = null;
		PreparedStatement ps = null;
		Statement s  = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<ProjectHead> list = new ArrayList<>();	
		try {	
			c = DatabaseConnection.getConnection();			
			s = c.createStatement();
			rs = s.executeQuery(FIND_ALL);
			System.out.println();
			while (rs.next()) {
				ProjectHead head = new ProjectHead();
				head.setId(rs.getLong("ID"));
				head.setName(rs.getString("NAME"));
				head.setCreatingDate(new Date(rs.getLong("DATE")));
				head.setTableName(rs.getString("TABLE_NAME"));
				list.add(head);
			}
			rs.close();
			for (ProjectHead projectHead : list) {
				rs = s.executeQuery(GET_TABLE_LENGHT.replace("?", projectHead.getTableName()));
				rs.next();
				projectHead.setContent(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			try {
				DatabaseConnection.close(ps);
				DatabaseConnection.close(rs);
				DatabaseConnection.close(rs2);
				DatabaseConnection.close(s);
				DatabaseConnection.close(c);
				
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
		
		return list;
	}
	
	public void delete(ProjectHead data){
		Connection c = null;
		Statement s  = null;
		PreparedStatement ps = null;
		try {
			
			c = DatabaseConnection.getConnection();
			
			ps = c.prepareStatement(DELETE);
			ps.setLong(1, data.getId());
			ps.executeUpdate();			
			
			s = c.createStatement();
			s.executeUpdate(DROP_TABLE.replace("?", data.getTableName()));
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			try {
				DatabaseConnection.close(ps);
				DatabaseConnection.close(s);
				DatabaseConnection.close(c);
				
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
			
		}
	}
	
}
