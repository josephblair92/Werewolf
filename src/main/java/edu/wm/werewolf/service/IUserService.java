package edu.wm.werewolf.service;

public interface IUserService {

	public boolean createUser (String username, String password, String firstname, String lastname);
	public void removeUser (String username);
	
}
