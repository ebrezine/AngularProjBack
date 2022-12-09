package com.revature.daos;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return false;
	}

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
