package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean addUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (username, PASSWORD, is_worker) VALUES (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			if (user.isWorker()) {
				statement.setBoolean(++index, user.isWorker());
			} else {
				statement.setBoolean(++index, false);
			}

			statement.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ? DOES THIS METHOD NEED TO EXIST? We are getting a user by filtering the
	// ? employee list and returning just the user that has logged in
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			List<User> users = new ArrayList<>();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setWorker(resultSet.getBoolean("is_worker"));

				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
