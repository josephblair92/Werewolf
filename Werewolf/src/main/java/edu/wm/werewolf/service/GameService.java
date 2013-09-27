package edu.wm.werewolf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.dao.IKillDAO;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.Player;

public class GameService {
	
	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDao;
	@Autowired private IKillDAO killDao;
	@Autowired private IGameDAO gameDao;
	
	public List<Player> getAllAlive() {
		
//		List<Player> players = new ArrayList<Player>();
//		players.add(new Player("edcba", false, 37.754, -77.3825, "fghijkl", false));
//		return players;
		return playerDAO.getAllAlive();
	}
	
/*	
	public void updatePosition(String username, GPSLocation location)  {
		//User u = userDao.getUserByName(username);
		//playerDao
	}
*/

}
