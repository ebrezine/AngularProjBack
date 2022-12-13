package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.LoginController;

import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.Javalin;

public class Main {
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create();

		configure(new LoginController());

		Dotenv dotenv = Dotenv.load();

		int port = Integer.parseInt(dotenv.get("PORT", "8083"));

		app.start(port);
	}

	public static void configure(Controller... controllers) {
		for (Controller controller : controllers) {
			controller.addRoutes(app);
		}
	}
}
