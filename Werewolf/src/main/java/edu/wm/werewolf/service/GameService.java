package edu.wm.werewolf.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.dao.IKillDAO;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.Game;
import edu.wm.werewolf.domain.JsonResponse;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.domain.Score;
import edu.wm.werewolf.domain.User;
import edu.wm.werewolf.exceptions.GameNotFoundException;
import edu.wm.werewolf.exceptions.NoRemainingPlayersException;
import edu.wm.werewolf.exceptions.PlayerNotFoundException;

public class GameService {
	
	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDAO;
	@Autowired private IKillDAO killDAO;
	@Autowired private IGameDAO gameDAO;
	private String activeGameID;
	private int scentRadius = 800;
	private int killRadius = 500;
	
	public List<Player> getAllAlive() {
		return playerDAO.getAllAlive();
	}
	
	public JsonResponse vote(String voterUsername, String votingForUsername) {
		
		Player voter;
		try {
			voter = playerDAO.getPlayerByUsername(voterUsername);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Player votingFor;
		try {
			votingFor = playerDAO.getPlayerByUsername(votingForUsername);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Game g;
		try {
			g = gameDAO.getGameByID(activeGameID);
		} catch (GameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		if (voter.isDead())
			return new JsonResponse (false, "Error: you must be alive to vote for other players.");
		if (votingFor.isDead())
			return new JsonResponse (false, "Error: the player you have voted for is already dead.");
		if (g.atNight())
			return new JsonResponse (false, "Error: you can only vote during the daytime.");
		
		playerDAO.voteFor(voter, votingFor);
		return new JsonResponse(true, "Vote registered successfully.");	
		
	}
	
	public JsonResponse kill(String killerUsername, String victimUsername) {
		
		Player killer;
		try {
			killer = playerDAO.getPlayerByUsername(killerUsername);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Player victim;
		try {
			victim = playerDAO.getPlayerByUsername(killerUsername);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Game g;
		try {
			g = gameDAO.getGameByID(activeGameID);
		} catch (GameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		if (!killer.isWerewolf())
			return new JsonResponse (false, "Error: must be a werewolf to kill other players.");
		if (victim.isDead())
			return new JsonResponse (false, "Error: victim is already dead.");
		if (killer.isDead())
			return new JsonResponse (false, "Error: you must be alive to kill other players.");
		if (!g.atNight())
			return new JsonResponse (false, "Error: you can only kill players at night.");
		
		double playerDistance;
		try {
			playerDistance = playerDAO.getDistanceBetween(killer, victim);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResponse(false, "Error: player not found");
		}
		catch (SQLException e) {
			return new JsonResponse(false, "Error: victim is not within range.");
		}
		
		if (playerDistance < 0 || playerDistance > killRadius)
			return new JsonResponse(false, "Error: victim is not within range.");
		else  {
			playerDAO.setDead(victim);
			return new JsonResponse(true, "Victim killed successfully.");
		}
		
	}

	public List<Player> getAllNearby(String username) {

		Player p;
		try {
			p = playerDAO.getPlayerByUsername(username);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Game g;
		try {
			g = gameDAO.getGameByID(activeGameID);
		} catch (GameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		if (!p.isWerewolf() || p.isDead() || !g.atNight()) {
			return null;
		}
		
		return playerDAO.getAllNear(new GPSLocation(p.getLat(), p.getLng()), scentRadius);
		
		
	}

	public List<Player> getAllVotable() {
		return playerDAO.getAllAlive();
	}

	public List<Score> getScores() {
		return userDAO.getScores();
	}

	public JsonResponse newGame(String adminUsername, int dayNightFrequency) {

		Game g = new Game(adminUsername, Calendar.getInstance().getTime(), dayNightFrequency, null);
		activeGameID = gameDAO.newGame(g);
		
		playerDAO.deleteAllPlayers();
		List<User> users = userDAO.getAllUsers();
		
		List<Player> players = new ArrayList<Player>();
		
		for (int i = 0; i < users.size(); i++) {
			Player p = new Player(null, false, 999, 999, users.get(i).getUsername(), false, null, null);
			players.add(p);
		}
		
		int numberOfWerewolves = users.size() / 3;
		
		for (int i = 0; i < numberOfWerewolves; i++) {
			
			int index;
			
			do {
				index = (int)((Math.random() * users.size()) + 1);
			} while (players.get(index).isWerewolf());
			
			Player p = players.get(index);
			p.setWerewolf(true);
			players.set(index, p);
			
		}
		
		for (int i = 0; i < players.size(); i++)
			playerDAO.insertPlayer(players.get(i));
		
		JsonResponse r = new JsonResponse(true, "Sucessfully created new game");
		return r;
		
	}

	public JsonResponse restartGame(Game g, String username) {
		
		if (!g.getAdmin().equals(username)) {
			return new JsonResponse(false, "Error: only the admin is allowed to restart the game.");
		}
		
		gameDAO.restartGameByID(g.getGameID());
		playerDAO.restartGame();
		
		List<String> players = playerDAO.getAllIDs();
		List<String> werewolfIDs = new ArrayList<String>();
		
		int numberOfWerewolves = players.size() / 3;
		
		for (int i = 0; i < numberOfWerewolves; i++) {
			
			int index;
			
			do {
				index = (int)((Math.random() * players.size()) + 1);
			} while (werewolfIDs.contains(players.get(index)));
			
			werewolfIDs.add(players.get(index));
			
		}
		
		for (int i = 0; i < werewolfIDs.size(); i++) {
			playerDAO.setWerewolfByID(werewolfIDs.get(i));
		}
		
		return new JsonResponse(true, "Successfully restarted the game.");
	}
	
	public JsonResponse updatePosition(String username, GPSLocation location)  {
		
		playerDAO.moveByUsername(username, location);
		return new JsonResponse(true, "Player location updated successfully.");
		
	}
	
	public void tallyVotes() {
		
		try {
			playerDAO.logVotes();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		} catch (NoRemainingPlayersException e1) {
			e1.printStackTrace();
			return;
		}
		
	}

}
