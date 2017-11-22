package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Local {
	
	private String id;
	private String name;
	private String address;
	private String number;
	private String city;
	private String neighbourhood; 
	private String state; 
	private String zipcode;
	private User user;
	private Category category;
	private String description;
	private String createAt;
	private String lat;
	private String lng;
	
	public Local() {
		
	}
	
	public Local(User user, String name, String description, String address, String number, String city, String neighbourhood, String state, String zipcode, String lat, String lng) {
		this.setId(this.generateId());
		this.setUser(user);
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
		this.setNumber(number);
		this.setCity(city);
		this.setNeighbourhood(neighbourhood);
		this.setState(state);
		this.setZipcode(zipcode);
		this.setLat(lat);
		this.setLng(lng);
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
