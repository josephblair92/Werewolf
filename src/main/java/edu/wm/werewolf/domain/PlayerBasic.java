package edu.wm.werewolf.domain;

public class PlayerBasic {
	
	private String username;
	private String firstname;
	private String lastname;
	private String imageURL;
	
	public PlayerBasic(String username, String firstname, String lastname, String imageURL) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.imageURL = imageURL;
	}
	
	public PlayerBasic(String username, String firstname, String lastname) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

}
