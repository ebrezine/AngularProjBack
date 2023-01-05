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
<<<<<<< HEAD
=======
            System.out.println("==============SHOULD BE PRINTING CURRENT USERS ID:=====================");
            System.out.println(curr.getUsername());
            System.out.println(curr.isWorker());
>>>>>>> backEndWork1
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
            		ctx.json(claimService.getAllClaims(curr.getUsername()));
>>>>>>> backEndWork1
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
<<<<<<< HEAD
    	HttpSession session = ctx.req().getSession(false);
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(!user.isWorker()){
                Claim claim = ctx.bodyAsClass(Claim.class);
				claim.setUser_id(user.getId());		
                if(claim.getStatus() == null) {
			        claim.setStatus("pending");
			        claim.setPending(true);
		    }else{
                ctx.status(401);
            }
            
		if(claimService.createClaim(claim)) {
			ctx.status(201);
		}else {
			ctx.status(401);
		}
            }
=======
        Claim newclaim = ctx.bodyAsClass(Claim.class);

        int claim_id = newclaim.getClaim_id();
        int amount = newclaim.getAmount();
        String description = newclaim.getDescription();
        String status = newclaim.getStatus();
        String user_id = newclaim.getUser_id();

        if(claimService.createClaim(claim_id, amount, description, status, user_id)){
            System.out.println("============New Claim created===========");
            ctx.status(200);
>>>>>>> backEndWork1
        }
        
	};
    
    
    Handler setClaim = (ctx) -> {
    	HttpSession session = ctx.req().getSession();
		User user = (User) session.getAttribute("user");
		if(user.isWorker()) {
			Claim claim = ctx.bodyAsClass(Claim.class); //makes it much simpler to approve/deny claims
			if(claimService.changeClaim(claim)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}
        else{
            ctx.status(401);
        }
    };

    public void addRoutes(Javalin app){
        //app.get("/pending", getAllPendingClaims);
        app.get("/claims", getAllClaims);
        app.post("/claims", createClaim);
        app.put("/claims", setClaim);
    }


}
