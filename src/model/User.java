package model;

public class User {

	private int id;
	private String name;
	private String pwd;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
	}

	public String getPassword() {
		return this.pwd;
	}

}
