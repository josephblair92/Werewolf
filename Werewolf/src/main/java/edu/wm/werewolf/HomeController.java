package edu.wm.werewolf;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.dao.IKillDAO;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
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
	public @ResponseBody List<Player> getAllNearby() {
		logger.info("GET to /players/nearby - getAllNearby()");
		List<Player> nearbyPlayers = gameService.getAllNearby(null);
		return nearbyPlayers;
	}
	
	@RequestMapping(value = "/players/votable", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAllVotable() {
		logger.info("GET to /players/votable - getAllVotable()");
		List<Player> votablePlayers = gameService.getAllVotable();
		return votablePlayers;
	}
	
	@RequestMapping(value = "/players/scores", method = RequestMethod.GET)
	public @ResponseBody List<Score> getScores() {
		logger.info("GET to /scores - getScores()");
		List<Score> scores = gameService.getScores();
		return scores;
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public @ResponseBody JsonResponse updateLocation(@RequestParam("user") String user) {
		logger.info("POST to /location - updateLocation()");
		//JsonResponse response = gameService.updatePosition(null, null);
		JsonResponse response = new JsonResponse(true, "Test successful");
		logger.info(user);
		return response;
	}
	
	@RequestMapping(value = "/newgame", method = RequestMethod.POST)
	public @ResponseBody JsonResponse newGame() {
		logger.info("POST to /newgame - newGame()");
		JsonResponse response = gameService.newGame(null, 0);
		return response;
	}
	
	@RequestMapping(value = "/restartgame", method = RequestMethod.POST)
	public @ResponseBody JsonResponse restartGame() {
		logger.info("POST to /restartgame - restartGame()");
		JsonResponse response = gameService.restartGame(null, null);
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
	
	@RequestMapping(value="/player/{id}", method=RequestMethod.POST)
	public @ResponseBody JsonResponse castVote(@PathVariable int id, Principal principal) {
	}
	
*/
	
}
