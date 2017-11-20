package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.ConnectionFactory;
import models.Evaluation;
import models.Local;
import models.User;

public class EvaluationsRepository {
	
	public Evaluation create(Evaluation evaluation) {
		String query = "INSERT INTO evaluations (id, user_id, local_id, value, comment) VALUES (?, ?, ?, ?, ?)";
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, evaluation.getId());
			stmt.setString(2, evaluation.getUser().getId());
			stmt.setString(3, evaluation.getLocalId());
			stmt.setString(4, evaluation.getValue());
			stmt.setString(5, evaluation.getComment());
			
			stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return evaluation;
	}
	
	public ArrayList<Evaluation> getByLocalId(String localId) {
		String query = "SELECT * FROM evaluations INNER JOIN users ON evaluations.user_id = users.id WHERE evaluations.local_id = ?";
		ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt;
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, localId);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setEmail(result.getString("users.email"));
				user.setId(result.getString("users.id"));
				user.setName(result.getString("users.name"));
				
				Evaluation evaluation = new Evaluation();
				evaluation.setId(result.getString("evaluations.id"));
				evaluation.setLocalId(result.getString("evaluations.local_id"));
				evaluation.setComment(result.getString("evaluations.comment"));
				evaluation.setValue(result.getString("evaluations.value"));
				evaluation.setCreateAt(result.getString("evaluations.create_at"));
				evaluation.setUser(user);
				
				evaluations.add(evaluation);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return evaluations;
	}
	
}
