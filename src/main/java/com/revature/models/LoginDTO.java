package com.revature.models;

public class LoginDTO {
	public String email;
	public String password;
	public boolean isManager;

	public LoginDTO(String email, String password, boolean isManager) {
		super();
		this.email = email;
		this.password = password;
		this.isManager = isManager;
	}

	public LoginDTO() {
		super();
	}
}
