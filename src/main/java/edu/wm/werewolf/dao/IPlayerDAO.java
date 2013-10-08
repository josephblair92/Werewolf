package edu.wm.werewolf.dao;

import java.sql.SQLException;
import java.util.List;

import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.NoRemainingPlayersException;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public interface IPlayerDAO {

	Player getPlayerByID(String ID) throws PlayerNotFoundException;
	List<Player> getAllPlayers();
	List<Player> getAllAlive();
	//List<Player> getAllWerewolves();
	//List<Player> getAllTownspeople();
	List<Player> getAllNear(GPSLocation loc, int distance);
	List<String> getAllIDs();
	void insertPlayer(Player p);
	void deletePlayerByID(String ID);
	void deleteAllPlayers();
	void move(Player p, GPSLocation loc);
	void moveByUsername(String username, GPSLocation loc);
	void setDead(Player victim);
	void restartGame();
	void setWerewolfByID(String ID);
	Player getPlayerByUsername(String username) throws PlayerNotFoundException;
	double getDistanceBetween(Player first, Player second) throws PlayerNotFoundException, SQLException;
	void vote(Player voter, Player votingFor);
	void logVotes() throws SQLException, NoRemainingPlayersException;
	List<Player> getAliveWerewolves() throws SQLException;
	List<Player> getAliveTownspeople() throws SQLException;

}
