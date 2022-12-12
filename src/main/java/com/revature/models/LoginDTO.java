package com.revature.models;

public class LoginDTO {
	public String username;
	public String password;
	public boolean isWorker;

	public LoginDTO(String username, String password, boolean isWorker) {
		super();
		this.username = username;
		this.password = password;
		this.isWorker = isWorker;
	}

	public LoginDTO() {
		super();
	}
}
