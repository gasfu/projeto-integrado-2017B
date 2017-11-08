package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.ConnectionFactory;
import models.Local;

public class LocalsRepository {
	
	public Local create(Local local) {
		String query = "INSERT INTO locals (id, user_id, name, address, description) VALUES (?, ?, ?, ?, ?)";
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, local.getId());
			stmt.setString(2, local.getUser().getId());
			stmt.setString(3, local.getName());
			stmt.setString(4, local.getAddress());
			stmt.setString(5, local.getDescription());
			stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return local;
	}
	
	public ArrayList<Local> fetch() {
		String query = "SELECT * FROM locals";
		ArrayList<Local> locals = new ArrayList<Local>();
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Local local = new Local();
				local.setId(result.getString(1));
				local.setName(result.getString(3));
				local.setAddress(result.getString(4));
				local.setDescription(result.getString(5));
				local.setCreateAt(result.getString(6));
				
				locals.add(local);
			}
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return locals;
	}
	
}
