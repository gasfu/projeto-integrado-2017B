package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.ConnectionFactory;
import models.Category;

public class CategoriesRepository {
	
	public ArrayList<Category> fetch() {
		String query = "SELECT * FROM categories";
		ArrayList<Category> categories = new ArrayList<Category>();
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Category category = new Category();
				category.setId(result.getString("categories.id"));
				category.setLabel(result.getString("categories.label"));
				categories.add(category);
			}
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return categories;
	}
	
}
