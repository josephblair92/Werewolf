package edu.wm.werewolf.domain;

public class User {
	
	private String firstname;
	private String lastname;
	private String imageURL;
	private String hashedPassword;
	private String username;
	private int score;
	
	public User(String firstname, String lastname, String imageURL,
			String hashedPassword, String username, int score) {

		this.firstname = firstname;
		this.lastname = lastname;
		this.imageURL = imageURL;
		this.hashedPassword = hashedPassword;
		this.username = username;
		this.score = score;
	}
	
	public User(String username, String password) {
		this.username=username;
		this.hashedPassword=password;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
