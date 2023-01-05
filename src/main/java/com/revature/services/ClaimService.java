package com.revature.services;

import java.util.List;

import com.revature.daos.ClaimDAO;
import com.revature.models.Claim;
import com.revature.models.ClaimHelper;

//import com.revature.utils.ConnectionUtil;

public class ClaimService {

    private ClaimDAO claim_dao = new ClaimDAO();

    //We want to be able to Create New Tickets
<<<<<<< HEAD
    public boolean createClaim(Claim claim){

        //We do not need to give the claim ID --> will use DB connection for that
        //We do not need to set pending boolean statu
        boolean claim_created = claim_dao.createClaim(claim);
=======
    public boolean createClaim(int claim_id, int amount, String description, String status, String user_id){

        //We do not need to give the claim ID --> will use DB connection for that
        //We do not need to set pending boolean statu
    	
    	claim_id = claim_dao.getTotalClaims().size()+1;
    	status = "pending";
    	
        Claim new_claim = new Claim(claim_id,amount,description,status,user_id,true);
        boolean claim_created = claim_dao.createClaim(new_claim);
>>>>>>> backEndWork1

        return claim_created;

    }

    //Get All Pending Claims regardless of ID
    public List<Claim> get_claim_by_user(int id){
        //Use DAO function, standard stuff
        return claim_dao.get_claim_by_user(id);
    }


<<<<<<< HEAD
    public List<Claim> getPendingClaims(){
        return claim_dao.getPendingClaims();
=======
    public List<Claim> getAllClaims(String username){
        return claim_dao.getAllClaims(username);
>>>>>>> backEndWork1
    }
    //Get All Claims of a User by Passing ID
    
    public boolean changeClaim(Claim claim) {
    	return claim_dao.setClaim(claim);
    }

}
