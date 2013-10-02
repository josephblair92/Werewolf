package edu.wm.werewolf.domain;

public class Score {
	
	private String username;
	private int score;
	
	public Score(String username, int score) {
		super();
		this.username = username;
		this.score = score;
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
