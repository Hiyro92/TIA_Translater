package bean;

import java.util.Date;

public class ProjectHead {
	
	private long id;
	private String name;
	private Date creatingDate;
	private int content;
	private String tableName;
		
	public ProjectHead() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatingDate() {
		return creatingDate;
	}
	public void setCreatingDate(Date creatingDate) {
		this.creatingDate = creatingDate;
	}
	public int getContent() {
		return content;
	}
	public void setContent(int content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}	
	
	
	
}
