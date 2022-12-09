package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.LoginController;

import io.javalin.Javalin;

public class Main {
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create();

		configure(new LoginController());

		app.start(8083);
	}

	public static void configure(Controller... controllers) {
		for (Controller controller : controllers) {
			controller.addRoutes(app);
		}
	}
}
