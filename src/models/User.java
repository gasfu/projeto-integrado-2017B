package models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private String id;
	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password) {
		this.setId(this.generateId());
		this.setName(name);
		this.setEmail(email);
		try {
			this.setPassword(hash(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public User(String id, String name, String email, String password) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String generateId() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String string = "";
		
		while(string.length() <= 7) {
			int random = (int) Math.floor(Math.random() * characters.length());
			string += characters.charAt(random);
		}
		
		return string;
	}
	
	public static String hash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		algorithm.update(password.getBytes("UTF-8"));
		String hash = new BigInteger(1, algorithm.digest()).toString(16);
        return hash;
	}

}
