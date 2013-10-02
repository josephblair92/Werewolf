package edu.wm.werewolf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.text.DateFormatter;

import edu.wm.werewolf.domain.Game;
import edu.wm.werewolf.domain.Kill;
import edu.wm.werewolf.exceptions.GameNotFoundException;
import edu.wm.werewolf.exceptions.KillNotFoundException;

public class PostgresGameDAO extends PostgresDAO implements IGameDAO {

	@Override
	public Game getGameByID(String ID) throws GameNotFoundException {
		Connection connection = establishConnection();
		ResultSet r = execQuery(connection, "select * from game where id=" + ID +";");
		
		try {
			if (r.next())
				return new Game (r.getString("admin_username"), r.getDate("created_date"), r.getInt("day_night_frequency"), r.getString("id"));
			else
				throw new GameNotFoundException();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	@Override
	public String newGame(Game g) {
		
		Connection connection = establishConnection();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		ResultSet r = execQuery(connection, "insert into game(created_date, frequency, admin_username) values ('" + df.format(g.getCreatedDate()) + "'," + g.getDayNightFrequency() + ", '" + g.getAdmin() + "') returning id;");
		
		try {
			if (r.next())
				return r.getString("id");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public void removeGameByID(String ID) {
		
		Connection connection = establishConnection();
		execQuery(connection, "delete from game where id=" + ID);
		
	}
	
	public void restartGameByID(String ID) {
		
		Connection connection = establishConnection();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		
		execQuery(connection, "update game set created_date=" + df.format(Calendar.getInstance().getTime()) + " where id =" + ID + ";");
		
	}

}
