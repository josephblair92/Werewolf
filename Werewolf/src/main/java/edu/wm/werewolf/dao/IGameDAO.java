package edu.wm.werewolf.dao;

import edu.wm.werewolf.domain.Game;
import edu.wm.werewolf.exceptions.GameNotFoundException;

public interface IGameDAO {
	
	Game getGameByID(String ID) throws GameNotFoundException;
	void removeGameByID(String ID);
	void restartGameByID(String ID);
	String newGame(Game g);

}
