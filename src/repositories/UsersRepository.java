package repositories;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factories.ConnectionFactory;
import models.User;

public class UsersRepository {
	
	public User create(User user) {
		String query = "INSERT INTO users (id, email, name, password) VALUES (?, ?, ?, ?)";
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getPassword());
			stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User authorize(String email, String password) {
		String query = "SELECT * FROM users WHERE email = ? AND password = ?"; 
		User user = null;
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next())
				user = new User(result.getString("id"), result.getString("name"), result.getString("email"), result.getString("password"));			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;	
	}
	
	public User findOneByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?"; 
		User user = null;
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);

			ResultSet result = stmt.executeQuery();
			
			if(result.next())
				user = new User(result.getString("id"), result.getString("name"), result.getString("email"), result.getString("password"));			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;	
	}
	
}
