package edu.wm.werewolf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.werewolf.HomeController;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public class PostgresPlayerDAO implements IPlayerDAO {

	@Override
	public List<Player> getAllAlive() {
		
		Connection connection = PostgresDatabaseConnection.establishConnection();
		ResultSet r = execQuery(connection, "select * from player where is_dead=false;");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("userid"), r.getBoolean("is_werewolf")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	
	}

	private ResultSet execQuery(Connection con, String query) {
		try {
			Statement stmt = con.createStatement();
			HomeController.logger.info(query);
			return stmt.executeQuery(query);
		}
		catch (SQLException e) {
			HomeController.logger.info("Query failed: " + e.getMessage());
			return null;
		}

		
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
