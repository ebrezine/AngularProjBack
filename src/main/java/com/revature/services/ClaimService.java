package com.revature.services;

import java.util.List;

import com.revature.daos.ClaimDAO;
import com.revature.models.Claim;
import com.revature.models.ClaimHelper;

//import com.revature.utils.ConnectionUtil;

public class ClaimService {

    private ClaimDAO claim_dao = new ClaimDAO();

    //We want to be able to Create New Tickets
    public boolean createClaim(int claim_id, int amount, String description, String status, String user_id){

        //We do not need to give the claim ID --> will use DB connection for that
        //We do not need to set pending boolean statu
    	
    	claim_id = claim_dao.getTotalClaims().size()+1;
    	status = "pending";
    	
        Claim new_claim = new Claim(claim_id,amount,description,status,user_id,true);
        boolean claim_created = claim_dao.createClaim(new_claim);

        return claim_created;

    }

    //Get All Pending Claims regardless of ID
    public List<Claim> getAllPendingClaims(){
        //Use DAO function, standard stuff
        return claim_dao.getPendingClaims();
    }


    public List<Claim> getAllClaims(String username){
        return claim_dao.getAllClaims(username);
    }
    //Get All Claims of a User by Passing ID
    
    public boolean changeClaim(int id, String status) {
    	return claim_dao.setClaim(id, status);
    }

}
