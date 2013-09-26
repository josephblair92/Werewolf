package edu.wm.werewolf.domain;

import java.util.Date;

public class Game {
	
	private Date createdDate;
	private int dayNightFrequency;
	private String gameID;
	private boolean isRunning;
	private long timer;
	
	public Game(Date createdDate, int dayNightFrequency, String ID) {
		this.createdDate = createdDate;
		this.dayNightFrequency = dayNightFrequency;
		this.setGameID(ID);
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getDayNightFrequency() {
		return dayNightFrequency;
	}
	public void setDayNightFrequency(int dayNightFrequency) {
		this.dayNightFrequency = dayNightFrequency;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	
	

}
