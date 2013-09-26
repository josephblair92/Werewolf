package edu.wm.werewolf.dao;

import edu.wm.werewolf.domain.User;

public interface IUserDAO {
	
	void insertUser(User u);
	void removeUserByID(String ID);
	void setPicture(String filepath);
	User getUserByID(String ID);

}
