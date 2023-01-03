package com.revature;

import com.revature.controllers.ClaimController;
import com.revature.controllers.Controller;
import com.revature.controllers.LoginController;

import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.Javalin;

public class Main {
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create(config -> {
			config.plugins.enableCors(cors -> {
				cors.add(it -> {
					it.allowHost("http://localhost:4200");
					it.allowCredentials = true;
					it.exposeHeader("x-server");
				});
			});
		});

		configure(new ClaimController(), new LoginController());

		Dotenv dotenv = Dotenv.load();

		int port = Integer.parseInt(dotenv.get("PORT", "8083"));

		app.start(port);
	}

	public static void configure(ClaimController claim_controller, Controller... controllers) {
		for (Controller controller : controllers) {
			controller.addRoutes(app);
		}
		claim_controller.addRoutes(app);

	}
}
