package com.revature.services;

import java.util.List;

import com.revature.daos.ClaimDAO;
import com.revature.models.Claim;

//import com.revature.utils.ConnectionUtil;

public class ClaimService {

    private ClaimDAO claim_dao = new ClaimDAO();

    //We want to be able to Create New Tickets
    public boolean createClaim(int claim_id, int amount, String description, String status, int user_id){

        //We do not need to give the claim ID --> will use DB connection for that
        //We do not need to set pending boolean statu
        Claim new_claim = new Claim(claim_id,amount,description,status,user_id,true);
        boolean claim_created = claim_dao.createClaim(new_claim);

        return claim_created;

    }

    //Get All Pending Claims regardless of ID
    public List<Claim> getAllPendingClaims(){
        //Use DAO function, standard stuff
        return claim_dao.getPendingClaims();
    }


    public List<Claim> getAllClaims(int id){
        return getAllClaims(id);
    }
    //Get All Claims of a User by Passing ID


}
