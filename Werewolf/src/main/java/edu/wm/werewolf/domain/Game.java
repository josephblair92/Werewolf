package edu.wm.werewolf.domain;

import java.util.Calendar;
import java.util.Date;

import edu.wm.werewolf.HomeController;

public class Game {
	
	private Date createdDate;
	private int dayNightFrequency;
	private String gameID;
	private boolean isRunning;
	private String admin;
	
	public Game(String admin, Date createdDate, int dayNightFrequency, String ID) {
		this.admin = admin;
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public boolean atNight() {

		Date now = Calendar.getInstance().getTime();
		long elapsedMinutes = (now.getTime() - createdDate.getTime()) / 60000;
		return (elapsedMinutes / dayNightFrequency) % 2 != 0;
		
	}
	
	
	
	

}
