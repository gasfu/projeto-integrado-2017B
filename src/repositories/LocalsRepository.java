package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.ConnectionFactory;
import models.Local;
import models.User;

public class LocalsRepository {
	
	public Local create(Local local) {
		String query = "INSERT INTO locals (id, user_id, name, address, zipcode, lat, lng, neighbourhood, city, state, number, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, local.getId());
			stmt.setString(2, local.getUser().getId());
			stmt.setString(3, local.getName());
			stmt.setString(4, local.getAddress());
			stmt.setString(5, local.getZipcode());
			stmt.setString(6, local.getLat());
			stmt.setString(7, local.getLng());
			stmt.setString(8, local.getNeighbourhood());
			stmt.setString(9, local.getCity());
			stmt.setString(10, local.getState());
			stmt.setString(11, local.getNumber());
			stmt.setString(12, local.getDescription());
			
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
				local.setZipcode(result.getString(6));
				local.setState(result.getString(7));
				local.setCity(result.getString(8));
				local.setLat(result.getString(11));
				local.setLng(result.getString(12));
				locals.add(local);
			}
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return locals;
	}
	
	public ArrayList<Local> search(String name) {
		String query = "SELECT * FROM locals WHERE name LIKE ?";
		ArrayList<Local> locals = new ArrayList<Local>();
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, name + "%");
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Local local = new Local();
				local.setId(result.getString(1));
				local.setName(result.getString(3));
				local.setAddress(result.getString(4));
				local.setDescription(result.getString(5));
				local.setZipcode(result.getString(6));
				local.setState(result.getString(7));
				local.setCity(result.getString(8));
				local.setLat(result.getString(11));
				local.setLng(result.getString(12));
				locals.add(local);
			}
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return locals;
	}
	
	public Local getLocalById(String id) {
		String query = "SELECT * FROM locals INNER JOIN users ON locals.user_id = users.id WHERE locals.id = ?";
		Local local = null;
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				User user = new User();
				user.setEmail(result.getString("users.email"));
				user.setId(result.getString("users.id"));
				user.setName(result.getString("users.name"));
				
				local = new Local();
				local.setId(result.getString("locals.id"));
				local.setZipcode(result.getString("locals.zipcode"));
				local.setAddress(result.getString("locals.address"));
				local.setNeighbourhood(result.getString("locals.neighbourhood"));
				local.setCity(result.getString("locals.city"));
				local.setState(result.getString("locals.state"));
				local.setLat(result.getString("locals.lat"));
				local.setLng(result.getString("locals.lng"));
				local.setNumber(result.getString("locals.number"));
				local.setCreateAt(result.getString("locals.create_at"));
				local.setName(result.getString("locals.name"));
				local.setDescription(result.getString("description"));
				local.setUser(user);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return local;
	}
	
}
