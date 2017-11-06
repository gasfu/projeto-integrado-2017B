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
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
