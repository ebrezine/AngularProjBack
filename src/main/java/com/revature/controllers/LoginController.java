package com.revature.controllers;

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
			ctx.status(400);
			ctx.json("Email and/or password is empty, please try again");
			return;
		}

		// attempt login with username and password
		if (loginService.login(attempt.username, attempt.password) != null) {
			User user = loginService.login(attempt.username, attempt.password);

			// create a new session
			HttpSession session = ctx.req().getSession();

			// add the user to the session, return 200 status
			session.setAttribute("user", user);
			ctx.status(200);
		} else {
			// if no user was found, send 401 status
			ctx.status(401);
			ctx.json("There was no user found with that email and pasword, try again or register for access.");
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		// TODO Auto-generated method stub

	}
}
