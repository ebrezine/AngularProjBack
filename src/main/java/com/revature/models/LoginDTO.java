package com.revature.models;

public class LoginDTO {
	public String username;
	public String password;
	public boolean isWorker;
	public String newPassword;

	public LoginDTO(String username, String password, boolean isWorker, String newPassword) {
		super();
		this.username = username;
		this.password = password;
		this.isWorker = isWorker;
		this.newPassword = newPassword;
	}

	public LoginDTO() {
		super();
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

	public boolean isWorker() {
		return isWorker;
	}

	public void setWorker(boolean isWorker) {
		this.isWorker = isWorker;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
