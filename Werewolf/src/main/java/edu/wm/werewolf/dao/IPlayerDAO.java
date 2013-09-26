package edu.wm.werewolf.dao;

import java.util.List;

import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public interface IPlayerDAO {

	Player getPlayerByID(String ID) throws PlayerNotFoundException;
	List<Player> getAllAlive();
	List<Player> getAllWerewolves();
	List<Player> getAllTownspeople();
	List<Player> getAllNear(double lat, double lng);
	void insertPlayer(Player p);
	void deletePlayerByID(String ID);
	void move(Player p, double lat, double lng);
	void kill(Player killer, Player victim);
	void setDead(Player victim);

}
