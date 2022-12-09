package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class LoginService {
	private UserDAO userDAO = new UserDAOImpl();

	public User login(String username, String password) {
		List<User> users = userDAO.getUsers();

		try {
			for (User user : users) {
				if (user.getUsername().matches(username) && user.getPassword().matches(password)) {
					return user;
				}
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public boolean register(User user) {
		return userDAO.addUser(user);
	}
}
