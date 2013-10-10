package edu.wm.werewolf.service;

public interface IUserService {

	public boolean createUser (String username, String password);
	public void removeUser (String username);
	
}
