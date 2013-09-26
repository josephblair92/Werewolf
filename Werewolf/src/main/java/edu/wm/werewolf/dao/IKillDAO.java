package edu.wm.werewolf.dao;

import edu.wm.werewolf.domain.Kill;

public interface IKillDAO {
	
	void logKill (Kill k);
	Kill getKill(String killerID, String victimID);

}
