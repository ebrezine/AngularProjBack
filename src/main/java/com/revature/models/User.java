package com.revature.models;

public class User {
	private int id;
	private String username;
	private String password;
	private boolean isWorker;

	public User(int id, String username, String password, boolean isWorker) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isWorker = isWorker;
	}

	public User(String username, String password, boolean isWorker) {
		super();
		this.username = username;
		this.password = password;
		this.isWorker = isWorker;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (isWorker ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (isWorker != other.isWorker)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isWorker=" + isWorker + "]";
	}
}
