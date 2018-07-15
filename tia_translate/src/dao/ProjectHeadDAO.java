package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.ProjectHead;

public class ProjectHeadDAO {
	final private Logger LOGGER = LogManager.getLogger();
	
	private Connection c;
	private Statement s;
	
	public void add(ProjectHead data){
		try {
			c = DriverManager.getConnection("jdbc:sqlite:Data.db");
			s = c.createStatement();
			String sql = "INSERT INTO PROJECT_HEAD (NAME, DATE) VALUES ("
					+	 "'"  + data.getName() 						 + "' ,"
					+ 			data.getCreatingDate().getTime()	 + ")";	
			s.executeUpdate(sql);
			sql = 		"CREATE TABLE " + data.getName().toUpperCase() 
					+	"(ID INT PRIMARY KEY NOT NULL)"; 
			s.executeUpdate(sql);
		} catch (SQLException e) {
			LOGGER.error("Datenbank fehler : " + data.getName() + " kann nicht erstellt werden");
		}
		finally {
			try {
				if (c != null) {
					c.close();
				}
				if (s != null) {
					s.close();
				}
			}catch (SQLException e) {
			}
			
		}
	}
}
