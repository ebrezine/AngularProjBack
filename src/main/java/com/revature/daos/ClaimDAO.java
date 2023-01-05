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
<<<<<<< HEAD
    public List<Claim> get_claim_by_user(int id) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM claims WHERE created_by = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
=======
    public List<Claim> getPendingClaims(){
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM claims WHERE status = 'pending';";
>>>>>>> backEndWork1

			statement.setInt(1,  id);
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Claim> claims = new ArrayList<>();

<<<<<<< HEAD
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
=======
            List<Claim> pendingClaims = new ArrayList<>();

            while(resultSet.next()){
                Claim claim = new Claim();

                claim.setClaim_id(resultSet.getInt("id"));
                claim.setAmount(resultSet.getInt("amount"));
                claim.setDescription(resultSet.getString("description"));
                claim.setStatus(resultSet.getString("status"));
                claim.setUser_id(resultSet.getString("created_by"));
                claim.setPending(resultSet.getBoolean("pending"));

                pendingClaims.add(claim);

            }
            return pendingClaims;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //Get All Claims of a User by quierying their ID
    public List<Claim> getAllClaims(String username){
        try(Connection connection = ConnectionUtil.getConnection()){

        String sql = "SELECT * FROM claims WHERE created_by = ?;"; //May or may not work
        //May have to be a prepared statement

        
        PreparedStatement statement = connection.prepareStatement(sql);
        //Statement statement = connection.createStatement();
        statement.setString(1,  username);
        ResultSet resultSet = statement.executeQuery();

        List<Claim> userClaims = new ArrayList<>();

        while(resultSet.next()){
            Claim claim = new Claim();

                claim.setClaim_id(resultSet.getInt("id"));
                claim.setAmount(resultSet.getInt("amount"));
                claim.setDescription(resultSet.getString("description"));
                claim.setStatus(resultSet.getString("status"));
                claim.setUser_id(resultSet.getString("created_by"));
                claim.setPending(resultSet.getBoolean("pending"));

                userClaims.add(claim);
        }
        return userClaims;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public List<Claim> getTotalClaims(){
        try(Connection connection = ConnectionUtil.getConnection()){

        String sql = "SELECT * FROM claims"; //May or may not work
        //May have to be a prepared statement

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Claim> totalUserClaims = new ArrayList<>();

        while(resultSet.next()){
            Claim claim = new Claim();

                claim.setClaim_id(resultSet.getInt("id"));
                claim.setAmount(resultSet.getInt("amount"));
                claim.setDescription(resultSet.getString("description"));
                claim.setStatus(resultSet.getString("status"));
                claim.setUser_id(resultSet.getString("created_by"));
                claim.setPending(resultSet.getBoolean("pending"));

                totalUserClaims.add(claim);
        }
        return totalUserClaims;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    

    public boolean createClaim(Claim new_claim){
        //ID and BOOLEAN should be immediately assigned. I am not quite sure how to do that though.
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO claims (id, amount, description, status, created_by, pending) VALUES(?,?,?,?,?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            int index = 0; //Q4T <-- FOR SURE RIGHT HERE
            statement.setInt(++index, new_claim.getClaim_id()); //this should be an integer no because it is an ID,  QUESTION 4 TIM
            statement.setInt(++index, new_claim.getAmount());
            statement.setString(++index, new_claim.getDescription());
            statement.setString(++index, new_claim.getStatus());
            statement.setString(++index, new_claim.getUser_id());
            statement.setBoolean(++index, new_claim.isPending());

            statement.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println("=========ERROR ADDING CLAIM TO DB===========");
            e.printStackTrace();
            System.out.println("===============STACK TRACE ^^^^================");
            return false;
        }
        
    }
    
    public boolean setClaim(ClaimHelper claim) {
		try(Connection connection = ConnectionUtil.getConnection()){
>>>>>>> backEndWork1
			
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

