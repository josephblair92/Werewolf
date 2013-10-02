package edu.wm.werewolf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.wm.werewolf.domain.Kill;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.KillNotFoundException;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public class PostgresKillDAO extends PostgresDAO implements IKillDAO {

	@Override
	public void logKill(Kill k) {

		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "insert into kill (killer_id, victim_id, time, lat, lng) values (" + k.getKillerID() + "," + k.getVictimID() + "," + k.getTimestamp() + "," + k.getLat() + "," + k.getLng() + ");");
		
	}

	@Override
	public Kill getKill(String killerID, String victimID) throws KillNotFoundException {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from kill where killer_id=" + killerID + " and victim_id =" + victimID + ";");
		
		try {
			if (r.next())
				return new Kill (r.getString("killer_id"), r.getString("victim_id"), r.getDate("time"), r.getDouble("lat"), r.getDouble("lng"));
			else
				throw new KillNotFoundException();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
