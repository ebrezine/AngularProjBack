package com.revature.controllers;

import java.util.List;

import com.revature.models.Claim;
import com.revature.models.ClaimHelper;
import com.revature.services.ClaimService;
import com.revature.models.User;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;



public class ClaimController {
    
    private ClaimService claimService = new ClaimService();

    //Manager Only Function, or also automatically filled with session ID
    Handler getAllClaims = (ctx) -> {
    	HttpSession session = ctx.req().getSession(false);
    	if(session != null) {
    		User curr = (User) session.getAttribute("user");
            System.out.println("==============SHOULD BE PRINTING CURRENT USERS ID:=====================");
            System.out.println(curr.getId());
            System.out.println(curr.isWorker());
    		if (curr != null) {
    			if(curr.isWorker()) {
            		ctx.json(claimService.getPendingClaims());
            		ctx.status(200);
            	}
            	else
            	{
<<<<<<< HEAD
            		ctx.json(claimService.get_claim_by_user(curr.getId()));
=======
                    System.out.println("===================WE ARE HERE====================");
            		ctx.json(claimService.getAllClaims(curr.getId()));
>>>>>>> 7f8f308699dfc1d1fd3183c68ab603197bf9125f
                    ctx.status(200);
            	}
    		}
        	
    	}
    	else
    	{
    		ctx.status(400).json("{\"error\": \"Invalid credentials\"}");
    	}
    	
    /*
        String string_id = ctx.pathParam("user_id");

        //TODO Review this, not sure I am getting an integer correctly here
        int user_id = Integer.parseInt(string_id);
    
        try{
            int user_id = Integer.parseInt(string_id);
        }
        catch(NumberFormatException e){
            System.out.println("==========================Error Making String into Int========================");
            e.printStackTrace();
        }
        */
    /*    try{
            ctx.json(claimService.getAllClaims(user_id));
            ctx.status(200);
            return;
        }
        finally{
            System.out.println("=========Error Getting Claims by User ID : " + user_id + "======================");
            ctx.status(400);
        }
    */

    };

    //Creating a new Claim
    Handler createClaim = (ctx) -> {
		Claim claim = ctx.bodyAsClass(Claim.class);
		HttpSession session = ctx.req().getSession();
		User work = (User) session.getAttribute("user");
		claim.setUser_id(work.getId());		
        if(claim.getStatus() == null) {
			claim.setStatus("pending");
			claim.setPending(true);
		}
		if(claimService.createClaim(claim)) {
			ctx.status(201);
		}else {
			ctx.status(400);
		}
	};
    
    
    Handler setClaim = (ctx) -> {
    	HttpSession session = ctx.req().getSession();
		User user = (User) session.getAttribute("user");
		if(user.isWorker()) {
			ClaimHelper claim = ctx.bodyAsClass(ClaimHelper.class); //makes it much simpler to approve/deny claims
			if(claimService.changeClaim(claim)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}
    };

    public void addRoutes(Javalin app){
        //app.get("/pending", getAllPendingClaims);
        app.get("/claims", getAllClaims);
        app.post("/claims", createClaim);
        app.post("/changeclaim", setClaim);
    }


}
