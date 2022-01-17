package models;

public class User {

	private int id;
	private String name;
	private String password;
	private String type;
	private String date;
	
	public User(int id, String name, String password, String type, String date) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
		this.date = date;
	}

	public User(String name, String password, String type, String date) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
		this.date = date;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
		
}
