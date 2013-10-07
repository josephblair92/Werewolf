package edu.wm.werewolf.dao;

import java.sql.SQLException;
import java.text.ParseException;

import edu.wm.werewolf.domain.Game;
import edu.wm.werewolf.exceptions.GameNotFoundException;

public interface IGameDAO {
	
	Game getGameByID(String ID) throws GameNotFoundException;
	void removeGameByID(String ID);
	Game restartGameByID(String ID) throws SQLException, ParseException, GameNotFoundException;
	void newGame(Game g);

}
