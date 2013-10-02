package edu.wm.werewolf.dao;

import java.util.List;

import edu.wm.werewolf.domain.Score;
import edu.wm.werewolf.domain.User;
import edu.wm.werewolf.exceptions.UserNotFoundException;

public interface IUserDAO {
	
	void insertUser(User u);
	void removeUserByUsername(String username);
	void setPicture(String filepath);
	User getUserByUsername(String username) throws UserNotFoundException;
	List<User> getAllUsers();
	List<Score> getScores();
	void logWin(List<User> users);

}
