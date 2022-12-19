package com.revature.controllers;

import com.revature.models.Claim;
import com.revature.services.ClaimService;
import com.revature.models.User;



import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;



public class ClaimController {
    
    private ClaimService claimService = new ClaimService();

    /*
    Handler getAllPendingClaims = (ctx) -> {

        HttpSession session = ctx.req().getSession(false); //if session does not exist, false --> do not create one
        
        if(session != null)
        {
            //TODO would like to also get provider/patient status
            //LoginDTO user = (LoginDTO) session.getAttribute("user"); 
            //String username = user.username; //TODO would prefer to get provider status here

            ctx.json(claimService.getAllPendingClaims());
            ctx.status(200);
        }
        else
        {
            ctx.status(400).json("{\"error\": \"Invalid credentials\"}");
        }  
        
    };
*/
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
            		ctx.json(claimService.getAllPendingClaims());
            		ctx.status(200);
            	}
            	else
            	{
                    System.out.println("===================WE ARE HERE====================");
            		ctx.json(claimService.getAllClaims(curr.getId()));
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
        Claim newclaim = ctx.bodyAsClass(Claim.class);

        int claim_id = newclaim.getClaim_id();
        int amount = newclaim.getAmount();
        String description = newclaim.getDescription();
        String status = newclaim.getStatus();
        int user_id = newclaim.getUser_id();

        if(claimService.createClaim(claim_id, amount, description, status, user_id)){
            System.out.println("============New Claim created===========");
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }
    };

    public void addRoutes(Javalin app){
        //app.get("/pending", getAllPendingClaims);
        app.get("/claims", getAllClaims);
        app.post("/createClaim", createClaim);
    }


}
