package edu.wm.werewolf.dao;

import edu.wm.werewolf.domain.Game;

public interface IGameDAO {
	
	Game getGameByID(String ID);

}
