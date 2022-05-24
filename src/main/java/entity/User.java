package entity;

public class User {
	private String login_id;
	private String password;
	private String name;
	private int role;

	public User() {

	}

	public User(String login_id, String password, String name,int role) {
		this.login_id = login_id;
		this.name = name;
		this.password = password;
		this.role = role;

	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}