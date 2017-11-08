package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Local {
	
	private String id;
	private String name;
	private String address;
	private User user;
	private String description;
	private String createAt;
	
	public Local() {
		
	}
	
	public Local(User user, String name, String address, String description) {
		this.setId(this.generateId());
		this.setUser(user);
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
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
}
