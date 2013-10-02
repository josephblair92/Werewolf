package edu.wm.werewolf.domain;

import java.util.Date;

public class Player {

	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private String userID;
	private boolean isWerewolf;
	private String votedFor;
	private Date lastUpdate;
	
	public Player() {
		this.isDead = false;
		this.isWerewolf = false;
	}
	
	public Player(String id, boolean isDead, double lat, double lng,
			String userID, boolean isWerewolf, String votedFor, Date lastUpdate) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.userID = userID;
		this.isWerewolf = isWerewolf;
		this.votedFor = votedFor;
		this.lastUpdate = lastUpdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}

}
