package com.revature.services;

import java.util.List;

import com.revature.daos.ClaimDAO;
import com.revature.models.Claim;
import com.revature.models.ClaimHelper;

//import com.revature.utils.ConnectionUtil;

public class ClaimService {

    private ClaimDAO claim_dao = new ClaimDAO();

    //We want to be able to Create New Tickets
    public boolean createClaim(Claim claim){

        //We do not need to give the claim ID --> will use DB connection for that
        //We do not need to set pending boolean statu
        boolean claim_created = claim_dao.createClaim(claim);

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
    public List<Claim> getAllClaims(int id){
        return claim_dao.getAllClaims(id);
>>>>>>> 7f8f308699dfc1d1fd3183c68ab603197bf9125f
    }
    //Get All Claims of a User by Passing ID
    
    public boolean changeClaim(ClaimHelper claim) {
    	return claim_dao.setClaim(claim);
    }

}
