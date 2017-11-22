package models;

public class Evaluation {
	private User user;
	private String localId;
	private String id;
	private String wheelchairAccessValue;
	private String wheelchairWcValue;
	private String braileValue;
	private String tatilFloorValue;
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
	
	public Double getAverage() {
		double avg = (Integer.parseInt(this.getBraileValue()) + Integer.parseInt(this.getTatilFloorValue()) + Integer.parseInt(this.getWheelchairAccessValue()) + Integer.parseInt(this.getWheelchairWcValue()))/4;
		return avg;
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

	public String getWheelchairAccessValue() {
		return wheelchairAccessValue;
	}

	public void setWheelchairAccessValue(String wheelchairAccessValue) {
		this.wheelchairAccessValue = wheelchairAccessValue;
	}

	public String getWheelchairWcValue() {
		return wheelchairWcValue;
	}

	public void setWheelchairWcValue(String wheelchairWcValue) {
		this.wheelchairWcValue = wheelchairWcValue;
	}

	public String getBraileValue() {
		return braileValue;
	}

	public void setBraileValue(String braileValue) {
		this.braileValue = braileValue;
	}

	public String getTatilFloorValue() {
		return tatilFloorValue;
	}

	public void setTatilFloorValue(String tatilFloorValue) {
		this.tatilFloorValue = tatilFloorValue;
	}
}
