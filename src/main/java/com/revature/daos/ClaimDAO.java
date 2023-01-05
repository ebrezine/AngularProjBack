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
    public List<Claim> getPendingClaims(){
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM claims WHERE status = 'pending';";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

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
			
			String query = "SELECT * FROM claims WHERE id="+claim.id+";";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet tick = statement.executeQuery();
			Boolean pend = false; //have to assign some value, default false in case no ticket appears
			while(tick.next()) {
				pend = tick.getBoolean("pending"); //checking if ticket is pending
			}
				if(pend == true) { //making sure non-pending tickets can't be changed
					String sql = "UPDATE claims SET status = ?, pending=false WHERE id = ?;";
					statement = connection.prepareStatement(sql);
					statement.setString(1, claim.status);
					statement.setInt(2, claim.id);
					
					statement.execute();
					

					
					return true;
				}
				else {
					return false;
				}

			
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
    }		
}

