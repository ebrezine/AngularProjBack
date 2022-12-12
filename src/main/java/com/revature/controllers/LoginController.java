package com.revature.controllers;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {
	private LoginService loginService = new LoginService();

	// TODO - create custom exception to handle errors
	Handler login = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the user has included their username and password
		if (attempt.username == null || attempt.password == null) {
			ctx.status(400).json("{\"error\": \"Email and/or password is empty, please try again\"}");
			return;
		}

		// attempt login with username and password
		if (loginService.login(attempt.username, attempt.password) != null) {
			User user = loginService.login(attempt.username, attempt.password);

			// create a new session
			HttpSession session = ctx.req().getSession();

			// add the user to the session, return 200 status
			session.setAttribute("user", user);
			// TODO - redirect to homepage
			ctx.status(200);
		} else {
			// if no user was found, send 401 status
			ctx.status(401);
			ctx.json(
					"{\"error\": \"There was no user found with that email and password, try again or register for access.\"}");
		}
	};

	// TODO - create custom exception to handle errors
	Handler logout = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		// if session is null, return 400 status
		if (session == null) {
			ctx.status(400).json("{\"error\": \"No user is logged in.\"}");
			return;
		}

		// invalidate the session, send 200 status
		session.invalidate();
		// TODO - redirect to homepage
		ctx.status(200).json("{\"success\":\"Successfully logged out\"}");
	};

	// TODO - create custom exception to handle errors
	Handler register = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the attempted login has a username and password
		if (attempt.username == null || attempt.password == null) {
			ctx.status(400).json("{\"error\":\"Username or password is empty\"}");
			return;
		}

		User employee = new User(attempt.username, attempt.password, attempt.isWorker);

		// attempt to register the employee
		try {
			if (loginService.register(employee)) {
				// TODO - add user to session, redirect to homepage
				ctx.status(200).json(
						"{\"success\":\"A new user has been created with that username and password, proceed to login\"}");
			} else {
				ctx.status(401).json("{\"error\":\"There was an unknown issue creating this user\"}");
			}
		} catch (Exception e) {
			// if the employee exists already, send a 400 status
			// and the exception toString
			ctx.status(400).json("{\"error\":\"" + e.toString() + "\"}");
		}

	};

	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", login);
		app.post("/register", register);
		app.get("/logout", logout);
	}
}
