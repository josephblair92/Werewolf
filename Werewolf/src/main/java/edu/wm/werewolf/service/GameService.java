package edu.wm.werewolf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.dao.IKillDAO;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.Player;

public class GameService {
	
	@Autowired private IPlayerDAO playerDao;
	@Autowired private IUserDAO userDao;
	@Autowired private IKillDAO killDao;
	@Autowired private IGameDAO gameDao;
	
	public List<Player> getAllAlive() {
		return playerDao.getAllAlive();
	}
	
/*	
	public void updatePosition(String username, GPSLocation location)  {
		//User u = userDao.getUserByName(username);
		//playerDao
	}
*/

}
