package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public abstract List<User> getUsers();

	public abstract User getUser(int id);

	public abstract boolean addUser(User user);
	
	public abstract boolean changeStatus(User user);
	
	public abstract boolean userPwChange(String username, String newPassword);
}
