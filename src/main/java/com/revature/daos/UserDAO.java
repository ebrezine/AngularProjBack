package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public abstract List<User> getUsers();

	public abstract User getUser(int id);

	public abstract boolean addUser(User user);
}
