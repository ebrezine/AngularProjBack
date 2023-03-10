package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.ChangePassword;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {
	private LoginService loginService = new LoginService();

	Handler login = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the user has included their username and password
		if (attempt.username == null || attempt.password == null) {
			ctx.status(400).result("Email and/or password is empty, please try again");
			return;
		}

		// attempt login with username and password
		if (loginService.login(attempt.username, attempt.password) != null) {
			User user = loginService.login(attempt.username, attempt.password);

			// create a new session
			HttpSession session = ctx.req().getSession();

			// add the user to the session, return 200 status
			session.setAttribute("user", user);
			//session.setAttribute("email", attempt.username);
			//session.setAttribute("isWorker", attempt.isWorker);

			ctx.status(200).json(user);
			
		} else {
			// if no user was found, send 401 status
			ctx.status(401).result("There was no user found with that email and password--wrong email/password, try again or register for access.");
		}
	};

	Handler logout = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		// if session is null, return 400 status
		if (session == null) {
			ctx.status(400).result("No user is logged in.");
			return;
		}

		// invalidate the session, send 200 status
		session.invalidate();

		ctx.status(200);
	};

	Handler register = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the attempted login has a username and password
		if (attempt.username == null || attempt.password == null) {
			ctx.status(400).result("Username or password is empty");
			return;
		}

		User employee = new User(attempt.username, attempt.password, attempt.isWorker);

		// attempt to register the employee
		try {
			if (loginService.register(employee)) {
				// TODO - add user to session, send user to frontend
				ctx.status(200);
			} else {
				ctx.status(401).result("There was an unknown issue creating this user");
			}
		} catch (Exception e) {
			// if the employee exists already, send a 400 status
			// and the exception toString
			ctx.status(400).result(e.toString());
		}

	};

	Handler changeStatus = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		if (session == null) {
			ctx.status(400).json("No user is logged in.");
		} else {
			int pin = ctx.bodyAsClass(int.class);

			if (pin == 1234) {// magic number for changing class
				User curr = (User) session.getAttribute("user");
				if (curr.isWorker()) {
					curr.setWorker(false);

				} else {
					curr.setWorker(true);
				}
				UserDAOImpl UserDAO = new UserDAOImpl();
				UserDAO.changeStatus(curr);
			}
		}
	};

	Handler resetPassword = ctx -> {
		HttpSession session = ctx.req().getSession(false);
		ChangePassword userPwChange = ctx.bodyAsClass(ChangePassword.class);
		//User loggedInUser = (User) session.getAttribute("user");

		if (loginService.login(userPwChange.getChangePasswordUsername(), userPwChange.getChangePasswordPassword()) != null) {
			// User user = loginService.login(attempt.username, attempt.password);

			// HttpSession session = ctx.req().getSession();
			UserDAO userDAO = new UserDAOImpl();
			// String empRole = edao.getEmployeeByEmail(employee.getEmail()).getRole();
			if (userDAO.userPwChange(userPwChange.getChangePasswordUsername(), userPwChange.getChangePasswordNewPassword())) {

				//List<User> users = userDAO.getUsers();
				//ctx.json(users);
				// System.out.println(users);
				// CREATE USER JSON FORMAT IN RESPONSE////////////////////////////
				// session.setAttribute("role", empRole);
				// session.setAttribute("user", employee);
				// session.setAttribute("userEmail", employee.getEmail());
				// String userEmail = (String) session.getAttribute("userEmail");
				// System.out.println(userEmail + ":::getAtt");

				
				ctx.status(200);
				session.invalidate();
			} else {
				ctx.status(401).result("User is logged in but password change Unsuccessful (syntax?)");
			}

			// TODO - redirect to homepage

		} else {
			ctx.status(401).result("You are not logged in or wrong current password. Pw change unsuccessful");
		}

	};

	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", login);
		app.post("/register", register);
		app.get("/logout", logout);
		app.post("/change", changeStatus);
		app.post("/userprofile/resetpassword", resetPassword);
	}
}
