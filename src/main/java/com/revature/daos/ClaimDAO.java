package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Claim;
import com.revature.utils.ConnectionUtil;
import com.revature.models.ClaimHelper;

public class ClaimDAO {

    //Get all Pending Claims
    public List<Claim> get_claim_by_user(int id) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM claims WHERE created_by = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1,  id);
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Claim> claims = new ArrayList<>();

			while (resultSet.next()) {
				Claim claim = new Claim();
				claim.setDescription(resultSet.getString("description"));
				claim.setAmount(resultSet.getInt("amount"));
				claim.setUser_id(resultSet.getInt("created_by"));
				claim.setStatus((resultSet.getString("status")));
				claim.setPending((resultSet.getBoolean("pending")));
				claim.setClaim_id((resultSet.getInt("id")));
				claims.add(claim);
			}
			
			return claims;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

    //Get All Claims of a User by quierying their ID
    public List<Claim> getPendingClaims(){
        try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM claims WHERE pending=true;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			List<Claim> claims = new ArrayList<>();

			while (resultSet.next()) {

				Claim claim = new Claim();
				claim.setDescription(resultSet.getString("claim_description"));
				claim.setAmount(resultSet.getInt("amount"));
				claim.setUser_id(resultSet.getInt("created_by"));
				claim.setStatus((resultSet.getString("claim_status")));
				claim.setPending((resultSet.getBoolean("pending")));
				claim.setClaim_id((resultSet.getInt("id")));
				claims.add(claim);
			}
			
			return claims;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public boolean createClaim(Claim claim){
        //ID and BOOLEAN should be immediately assigned. I am not quite sure how to do that though.
        try(Connection connection = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO claims (amount, description, created_by, status, pending) VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			int index = 0;
			statement.setInt(++index, claim.getAmount());
			statement.setString(++index, claim.getDescription());
			statement.setInt(++index, claim.getUser_id());
			statement.setString(++index, claim.getStatus().toLowerCase());
			statement.setBoolean(++index, claim.isPending());

			
			
			statement.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
        
    }
    
    public boolean setClaim(Claim claim) {
		try(Connection connection = ConnectionUtil.getConnection()){
				
            String query = "SELECT * FROM claims WHERE id="+claim.getClaim_id()+";";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet tick = statement.executeQuery();
            Boolean pend = true;
            while(tick.next()) {
                pend = tick.getBoolean("pending");
            }
                if(pend == true) {
                    String sql = "UPDATE claims SET status = ?, pending=false WHERE id = ?;";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, claim.getStatus().toLowerCase());
                    statement.setInt(2, claim.getClaim_id());
                    
                    statement.execute();
                    

                    
                    return true;
                }
                else {
                    return false;
                }
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }		
}

