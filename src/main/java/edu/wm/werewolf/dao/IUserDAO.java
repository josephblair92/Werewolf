package edu.wm.werewolf.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.wm.werewolf.domain.Score;
import edu.wm.werewolf.domain.User;
import edu.wm.werewolf.exceptions.UserNotFoundException;

public interface IUserDAO {
	
	boolean insertUser(User u);
	boolean removeUserByUsername(String username);
	boolean setPicture(String filepath);
	User getUserByUsername(String username) throws UsernameNotFoundException;
	List<User> getAllUsers();
	List<Score> getScores();
	boolean logWin(User u);

}
