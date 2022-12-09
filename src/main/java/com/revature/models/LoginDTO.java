package com.revature.models;

public class LoginDTO {
	public String username;
	public String password;
	public boolean isManager;

	public LoginDTO(String username, String password, boolean isManager) {
		super();
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}

	public LoginDTO() {
		super();
	}
}
