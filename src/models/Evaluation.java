package models;

public class Evaluation {
	private User user;
	private String localId;
	private String id;
	private String value;
	private String createAt;
	private String comment;
	
	public Evaluation() {
		
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
