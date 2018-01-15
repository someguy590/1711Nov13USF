package com.reimb.pojos;


public class User {
	

	public User() {
	};

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", lastname=" + lastname
				+ ", name=" + name +", role=" + role +", email=" + email + "]";
	}

	public User(int id, String username, String password, String lastname, String name, String email, int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.name = name;
		this.email=email;
		this.role=role;
	}

	private String username;
	private String password;
	private String lastname;
	private String name;
	private String email;
	private int id;
	private int role;
	// define this within a switch or if statment
	// or enum . 

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}

