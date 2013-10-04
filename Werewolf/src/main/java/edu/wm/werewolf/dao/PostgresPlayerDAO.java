package edu.wm.werewolf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.wm.werewolf.HomeController;
import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.exceptions.NoRemainingPlayersException;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public class PostgresPlayerDAO extends PostgresDAO implements IPlayerDAO {

	@Override
	public List<Player> getAllAlive() {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where is_dead=false;");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("username"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	
	}

	@Override
	public Player getPlayerByID(String ID) throws PlayerNotFoundException {
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where id='" + ID + "';");
		
		try {
			if (r.next())
				return new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("username"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update"));
			else
				throw new PlayerNotFoundException();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Player> getAllWerewolves() {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where is_werewolf=true;");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("userid"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}

	@Override
	public List<Player> getAllTownspeople() {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where is_werewolf=false;");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("userid"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}

	@Override
	public List<Player> getAllNear(GPSLocation loc, int distance) {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where is_dead=false and ST_Distance(('POINT(' || player.lng || ' ' || player.lat || ')')::geography, 'POINT(" + loc.getLng() + " " + loc.getLat() + ")'::geography) < " + distance + ";");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("username"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}

	@Override
	public void insertPlayer(Player p) {
		
		Connection connection = establishConnection();
		execQuery(connection, "insert into player (username, is_werewolf, is_dead) values ('" + p.getUserID() + "'," + p.isWerewolf() + ", false);");
		
	}

	@Override
	public void move(Player p, GPSLocation loc) {
		
		Connection connection = establishConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		execQuery(connection, "update player set lat=" + loc.getLat() + ", lng=" + loc.getLng() + ", last_update='" + df.format(Calendar.getInstance().getTime()) + "' where id=" + p.getId() + ";");
		
	}

	public void moveByUsername(String username, GPSLocation loc) {
		
		Connection connection = establishConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		execQuery(connection, "update player set lat=" + loc.getLat() + ", lng=" + loc.getLng() + ", last_update='" + df.format(Calendar.getInstance().getTime()) + "' where username='" + username + "';");
		
	}
	
	@Override
	public void setDead(Player victim) {

		Connection connection = establishConnection();
		execQuery(connection, "update player set is_dead=true where id=" + victim.getId() + ";");
		
	}

	@Override
	public void deletePlayerByID(String ID) {

		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "delete from player where id=" + ID + ";");
		
	}

	@Override
	public void deleteAllPlayers() {

		Connection connection = establishConnection();
		execQuery(connection, "delete from player;");
		
	}

	@Override
	public List<Player> getAllPlayers() {

		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player;");
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			while (r.next())	
				players.add(new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("username"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update")));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
		
		
	}

	@Override
	public List<String> getAllIDs() {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select username from player;");
		
		List<String> IDs = new ArrayList<String>();
		
		try {
			while (r.next())
				IDs.add(r.getString("username"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return IDs;
		
	}

	@Override
	public void restartGame() {
		
		Connection connection = establishConnection();
		execQuery(connection, "update player set isdead=false, lat=null, lng=null, is_werewolf=false, last_update=null, voted_for=null;");
		
	}

	@Override
	public void setWerewolfByID(String ID) {

		Connection connection = establishConnection();
		execQuery(connection, "update player set is_werewolf=true where id=" + ID + ";");
		
	}

	@Override
	public Player getPlayerByUsername(String username) throws PlayerNotFoundException {
		
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from player where username='" + username + "';");
		
		try {
			if (r.next())
				return new Player(r.getString("id"), r.getBoolean("is_dead"), r.getDouble("lat"), r.getDouble("lng"), r.getString("username"), r.getBoolean("is_werewolf"), r.getString("voted_for"), r.getDate("last_update"));
			else
				throw new PlayerNotFoundException();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public double getDistanceBetween(Player first, Player second) throws PlayerNotFoundException, SQLException {

		Connection connection = establishConnection();
		ResultSet r1 = execQuery(connection, "select lat, lng from player where username='" + first.getUserID() + "';");
		
		double firstLat, firstLng, secondLat, secondLng;
		
		if (r1.next())  {
			firstLat = r1.getDouble("lat");
			firstLng = r1.getDouble("lng");
		}
		else
			throw new PlayerNotFoundException();

		
		ResultSet r2 = execQuery(connection, "select lat, lng from player where username='" + second.getUserID() + "';");
		

		if (r2.next())  {
			secondLat = r2.getDouble("lat");
			secondLng = r2.getDouble("lng");
		}
		else
			throw new PlayerNotFoundException();

		
		ResultSet r = execQuery(connection, "select ST_Distance('POINT(" + firstLng + " " + firstLat + ")'::geography, 'POINT(" + secondLng + " " + secondLat + ")'::geography) as dist;");
		
		if (r.next())
			return r.getDouble("dist");
		else
			return -1.0;
		
		
	}

	@Override
	public void voteFor(Player voter, Player votingFor) {

		Connection connection = establishConnection();
		execQuery(connection, "update player set voted_for='" + votingFor.getUserID() + "' where username = '" + voter.getUserID() + "';");
		
	}

	@Override
	public void logVotes() throws SQLException, NoRemainingPlayersException {
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from " +
				"(select voted_for, count(voted_for) as num_votes from player where is_dead=false and voted_for is not null group by voted_for) a, player b " +
				"where a.voted_for = b.username and b.is_dead=false order by num_votes;");
		
		String username;
		if (r.next())  {
			username = r.getString("username");
			execQuery(connection, "delete from player where username='" + username + "';");
		}
		else
			throw new NoRemainingPlayersException();

	}


}
