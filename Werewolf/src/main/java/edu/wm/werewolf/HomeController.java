package edu.wm.werewolf;

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.dao.IKillDAO;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.GPSLocation;
import edu.wm.werewolf.domain.JsonResponse;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.domain.Score;
import edu.wm.werewolf.service.GameService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired private GameService gameService;
	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDAO;
	@Autowired private IKillDAO killDAO;
	@Autowired private IGameDAO gameDAO;
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/players/alive", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAllAlive() {
		List<Player> alivePlayers = gameService.getAllAlive();
		return alivePlayers;
	}
	
	@RequestMapping(value = "/players/nearby", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAllNearby(Principal principal) {
		String username = principal.getName();
		//logger.info("GET to /players/nearby - getAllNearby(), username: " + username);
		List<Player> nearbyPlayers = gameService.getAllNearby(username);
		return nearbyPlayers;
	}
	
	@RequestMapping(value = "/players/votable", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAllVotable() {
		//logger.info("GET to /players/votable - getAllVotable()");
		List<Player> votablePlayers = gameService.getAllVotable();
		return votablePlayers;
	}
	
	@RequestMapping(value = "/players/scores", method = RequestMethod.GET)
	public @ResponseBody List<Score> getScores() {
		logger.info("GET to /scores - getScores()");
		List<Score> scores = gameService.getScores();
		return scores;
	}
	
	@RequestMapping(value="/players/{victimUsername}", method=RequestMethod.POST)
	public @ResponseBody JsonResponse killOrVote(@PathVariable String victimUsername, @ModelAttribute("action") String action, Principal principal) {
		
		String username = principal.getName();
		
		if (action.equals("kill")) {
			return gameService.kill(username, victimUsername);
		}
		else if (action.equals("vote")) {
			return gameService.vote(username, victimUsername);
		}
		else {
			return new JsonResponse(false, "Error: JSON request must specify which action you wish to take against the player.");
		}
		
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public @ResponseBody JsonResponse updateLocation(@ModelAttribute GPSLocation loc, Principal principal) {
		String username = principal.getName();
		JsonResponse response = gameService.updatePosition(username, loc);
		return response;
	}
	
	@RequestMapping(value = "/newgame", method = RequestMethod.POST)
	public @ResponseBody JsonResponse newGame(@ModelAttribute("numMinutes") String numMinutes, Principal principal) {
		//logger.info("POST to /newgame - newGame()");
		if (numMinutes.equals(""))
			return new JsonResponse(false, "You must specify a the number of minutes for each day/night cycle");
		
		String username = principal.getName();
		logger.info("username: " + username + " numMinutes: " + numMinutes);
		JsonResponse response = gameService.newGame(username, Integer.parseInt(numMinutes));
		return response;
	}
	
	@RequestMapping(value = "/restartgame", method = RequestMethod.POST)
	public @ResponseBody JsonResponse restartGame(Principal principal) {
		String username = principal.getName();
		//logger.info("POST to /restartgame - restartGame()");
		JsonResponse response = gameService.restartGame(username);
		return response;
	}

	
	/*
	@RequestMapping(value="/location", method=RequestMethod.POST)
	public @ResponseBody JsonResponse setLocation(@ModelAttribute GPSLocation location, Principal principal)
	{
		JsonResponse response = new JsonResponse();
		
		logger.info("Setting for " + principal.getName() + "s location to: " + location);
		gameService.updatePosition(principal.getName(), location);
		return response;
	}
	
*/
	
}
