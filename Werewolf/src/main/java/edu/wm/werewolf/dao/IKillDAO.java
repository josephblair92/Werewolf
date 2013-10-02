package edu.wm.werewolf.dao;

import edu.wm.werewolf.domain.Kill;
import edu.wm.werewolf.exceptions.KillNotFoundException;

public interface IKillDAO {
	
	void logKill (Kill k);
	Kill getKill(String killerID, String victimID) throws KillNotFoundException;

}
