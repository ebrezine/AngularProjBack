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

	// TODO - create custom exception to handle errors
	public boolean register(User user) throws Exception {
		List<User> users = userDAO.getUsers();

		for (User usr : users) {
			if (usr.getUsername().equals(user.getUsername())) {
				throw new Exception("A user with that email already exists, either login or reset your password.");
			}
		}

		return userDAO.addUser(user);
	}
	
	public boolean userPwChange(String username, String newPassword) {
		return userDAO.userPwChange(username, newPassword);
	}
}
