# Werewolf Web Service

####New user creation
Submit a POST request to /newuser, with the 'username,' 'password,' 'firstname,' and 'lastname' fields filled as desired.  A JsonResponse object will be returned indicating success or failure.

####New game creation
Submit a POST request to /newgame, providing proper user authentication and the field 'numMinutes' filled in with the desired number of minutes for each day and night (note that this value is the length of each, not both combined).  A JsonResponse object will be returned indicating success or failure.  On success, a new game will be started with the user who sent the request set as the admin of the game, adding all users in the database as players and immediately beginning the first day cycle.

####Getting a List of Alive Players (Players Who Can be Voted On)
Submit a GET request to /players/alive (or /players/votable), providing proper user authentication.  On success, a JSON serialization of player objects will be returned indicating alive players.  On failure, a null value will be returned.

####Submitting Votes
Submit a POST request to /players/{id}, where {id} is the username of the player you wish to vote for, providing proper user authentication and the field 'action' set to 'vote.'  Note that this can only be done during the day, and both you and the player for which you are voting must be alive.  If you submit more than one vote during the day, the previous vote will be overwritten.  A JsonResponse will be returned indicating success or failure.

####Updating Location
Submit a POST request to /location, providing proper user authentication and the fields 'lat' and 'lng' filled with the desired values.  A JsonResponse will be returned indicating success or failure.  Note that if this is not done at least once every 5 minutes, your player will automatically be killed.  However, the server will reject the request and automatically return a failure JsonResponse if a game is not active at the time of the request.

####Killing and detecting nearby players
This can only be done by werewolves, at night.  To get all nearby players, submit a GET request to /players/nearby, providing proper user authentication.  On success, a JSON serialization of player objects will be returned indicating players within range.  On failure, a null value will be returned.

In order to kill a player, submit a POST request to /players/{id}, where {id} is the username of the player you wish to kill, providing proper user authentication and setting the 'action' field to 'kill.'  A JsonResponse will be returned indicating success or failure.

####Restarting the Game
Submit a POST request to /restartgame, providing proper user authentication.  A JsonResponse will be returned indicating success or failure.  Note that this will only work if there is currently an active game and you are the admin of that game (ie, you created the game).  On success, all players will be set as alive, new werewolves will be randomly chosen, and the first day cycle will begin immediately.

####Viewing Scores
Submit a GET request to /scores to see how many games each user has won.  On success, a JSON serialization of users and their corresponding scores will be returned.  On failure, a null value will be returned.

###Test scripts
Run the file test_script.py included in the root directory.  Note that for the purposes of testing, automatic removal of "inactive" players and automatic switching of day and night has been disabled (this can be done manually by submitting a POST request to /day or /night).  However, these features can easily be re-enabled.