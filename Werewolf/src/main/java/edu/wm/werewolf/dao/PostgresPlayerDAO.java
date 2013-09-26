package edu.wm.werewolf.dao;

import java.util.List;

import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public class PostgresPlayerDAO implements IPlayerDAO {

	@Override
	public List<Player> getAllAlive() {
		// TODO Auto-generated method stub
		//List<Player> l = new List<Player>();
		//l.add(new Player("abcde", false, 37.754, -77.3825, "fghijkl", false));
		//return l;
		return null;
	}

	@Override
	public Player getPlayerByID(String ID) throws PlayerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllWerewolves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllTownspeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllNear(double lat, double lng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPlayer(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Player p, double lat, double lng) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(Player killer, Player victim) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDead(Player victim) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePlayerByID(String ID) {
		// TODO Auto-generated method stub
		
	}

}
