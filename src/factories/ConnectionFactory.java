package factories;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {
	
	static final String DB_HOST = "jdbc:mysql://localhost/acesse";
	static final String DB_USER = "root";
	static final String DB_PASS = "123";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
	}
	
}
