'''
Created on Sep 27, 2013

@author: Joseph
'''

import requests
from requests.auth import HTTPBasicAuth

if __name__ == '__main__':
    
    print("\n\nWelcome to the Werewolf testing script.\n\n")
    
    input('First we will test the ability to make a basic connection to the server.  If the connection is successful, you should see a printout of the HTML for the app homepage.  Press enter to continue.\n\n')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/')
    print(r.text + '\n')
    
    input('Next we will create 6 user accounts: user1, user2, user3, user4, user5 and user6, all with the password \'password\'.  Press enter to continue.\n')

    firstname='John'
    lastname='Doe'
    username='user1'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    firstname='Jane'
    lastname='Doe'
    username='user2'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    firstname='Rick'
    lastname='Roe'
    username='user3'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    firstname='Mary'
    lastname='Smith'
    username='user4'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    firstname='Joe'
    lastname='Schmoe'
    username='user5'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    firstname='John'
    lastname='Smith'
    username='user6'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    input('Next we will try to create an account with a username that is already taken.  This should result in an error message.  Press enter to continue.\n')
    
    firstname='John'
    lastname='Smith'
    username='user6'
    
    print('Creating user account ' + username + ' for ' + firstname + ' ' + lastname +':\n')

    payload={'username':username, 'password':'password', 'firstname':firstname, 'lastname':lastname}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text + '\n')
    
    input('Next, before creating a new game, we will try to update user1\'s location, something that can only be done once a game has been started.  This should return an error message.  Press enter to continue.\n')

    username='user1'
    payload={'lat':'33.4', 'lng':'-78.2'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=('user1', 'password'), data=payload)
    print(r.text + '\n')
    
    input('Now we will create a new game, with user2 as the admin.  Press enter to continue.\n')
    
    payload={'numMinutes':'5'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newgame', auth=('user2', 'password'), data=payload)
    print(r.text + '\n')
    
    input('Now that there is a game active, let\'s have user3 check the list of votable players.  There should be entries for all 6 users, giving both their username and real name.  Press enter to continue.\n')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user3', 'password'))
    print(r.text + '\n')
    
    input('Let\'s have user4 submit a request for all nearby players, something that can only be done at night by werewolves.  This should return nothing (as opposed to a JsonResponse indicating failure).  Press enter to continue.\n')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=('user4', 'password'))
    print(r.text + '\n')
    
    input('Let\'s have each player submit a vote.  As the script executes, you can see for whom each player votes.  Press enter to continue.\n')
    
    username='user1'
    votingfor='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user2'
    votingfor='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user3'
    votingfor='user1'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    votingfor='user1'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    votingfor='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    votingfor='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    input('Now that all votes have been registered, let\'s switch to the night cycle, tally the votes, and request the alive players to see that user2 has been killed.  Press enter to continue.\n')
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user3', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user4', 'password'))
    print(r.text + '\n')
    
    input('Let\'s have a player try to submit a vote.  This should return an error message since we are now in the night cycle.  Press enter to continue.\n')
    
    username='user4'
    votingfor='user1'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    input('Now each player will update their location.  You can see what each player\'s location is as the script runs.  Press enter to continue.\n')
    
    lat='33.750'
    lng='-78.820'
    username='user1'
    print(username + ' updating location to (' + lat + ', ' + lng + ')')
    payload={'lat':lat, 'lng':lng}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    lat='33.751'
    lng='-78.821'
    username='user3'
    print(username + ' updating location to (' + lat + ', ' + lng + ')')
    payload={'lat':lat, 'lng':lng}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    lat='82.350'
    lng='53.120'
    username='user4'
    print(username + ' updating location to (' + lat + ', ' + lng + ')')
    payload={'lat':lat, 'lng':lng}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    lat='82.351'
    lng='53.121'
    username='user5'
    print(username + ' updating location to (' + lat + ', ' + lng + ')')
    payload={'lat':lat, 'lng':lng}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    lat='82.352'
    lng='53.122'
    username='user6'
    print(username + ' updating location to (' + lat + ', ' + lng + ')')
    payload={'lat':lat, 'lng':lng}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    input('Let\'s have each player try to get a list of nearby players.  Only werewolves can do this, so for at least 4 out of the 6 players, the result will simply be blank.  The result should be blank for user2, since user2 has already been killed.  Press enter to continue.\n')
    
    username='user1'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user3'
    
    username='user2'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user3'
    
    username='user3'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user1'
    
    username='user4'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user6'
    
    username='user5'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user6'
    
    username='user6'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    if (r.text != ''):
        werewolf = username
        victim = 'user5'
        
    input('Based on the output, we can see that ' + werewolf + ' is a werewolf.  Let\'s have them try to kill ' + victim + ', who is in range.  We will then display the list of alive players to ensure that ' + victim + ' has been killed.  Press enter to continue.\n')
    payload={'action':'kill'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + victim, auth=(werewolf, 'password'), data=payload)
    print(r.text + '\n')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user4', 'password'))
    print(r.text + '\n')    
    
    input('Now we will have a player try to start a new game.  This should fail since there is already a game running.  Press enter to continue.\n')
    payload={'numMinutes':'5'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newgame', auth=('user1', 'password'), data=payload)
    print(r.text + '\n')
    
    input('However, if the admin of the current game restarts the game, this is allowed.  Press enter to continue.\n')
    r=requests.post('http://werewolf-jpblair.herokuapp.com/restartgame', auth=('user2', 'password'))
    print(r.text + '\n')
    
    input('We have now restarted the game and all players are alive again.  Let\'s run the game to completion by having one player be voted out each voting cycle.  We will also get the list of alive players after each voting cycle to see it shrink as expected.  The game will most likely end before this process completes.  Depending on when this happens, the last few lists will be blank and the vote submissions will return an error.  Press enter to continue.\n')
    
    votingfor = 'user1'
    
    username='user1'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    input('Press enter to begin the next voting cycle.\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user2'
    
    username='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    input('Press enter to begin the next voting cycle.\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user3'
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    input('Press enter to begin the next voting cycle.\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user4'

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    input('Press enter to begin the next voting cycle.\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user5'

    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    input('At this point the game is over, so let\'s check the scores to see who won.\n')
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/scores', auth=('user6', 'password'))
    print(r.text + '\n')
    
    input('Now we\'ll start another game and run it to completion in the same manner.  There will not be pauses for each day.  Press enter to continue.\n')
    payload={'numMinutes':'5'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newgame', auth=('user3', 'password'), data=payload)
    
    votingfor = 'user1'
    
    username='user1'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user2'
    
    username='user2'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user3'
    
    username='user3'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user4'

    username='user4'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/alive', auth=('user6', 'password'))
    print(r.text + '\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/day', auth=('user6', 'password'))
    
    votingfor = 'user5'

    username='user5'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')  
    
    username='user6'
    print(username + ' submitting vote for ' + votingfor)
    payload={'action':'vote'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/players/' + votingfor, auth=(username, 'password'), data=payload)
    print(r.text + '\n')
    
    print('Tallying votes\n')
    
    r=requests.post('http://werewolf-jpblair.herokuapp.com/night', auth=('user6', 'password'))
    
    input('At this point the game is over, so let\'s check the scores to see who won.\n')
    
    r=requests.get('http://werewolf-jpblair.herokuapp.com/scores', auth=('user6', 'password'))
    print(r.text + '\n')
    
    '''
    input('Let\'s have each player try to get a list of nearby players.  Only werewolves can do this, so for 4 out of the 6 players, the result will simply be blank.  Press enter to continue.\n')
    
    username='user1'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    username='user2'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    username='user3'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    username='user4'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    username='user5'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    username='user6'
    print(username + ' attempting to get all nearby players')
    r=requests.get('http://werewolf-jpblair.herokuapp.com/players/nearby', auth=(username, 'password'))
    print(r.text + '\n')
    
    payload={'lat':'33.4', 'lng':'-78.2'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/location', auth=('user2', 'password'), data=payload)
    print(r.text)
    '''
    
    '''
    payload={'username':'test_user', 'password':'test_pw22', 'firstname':'John', 'lastname':'Doe'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text)
    '''
    
    '''
    payload={'username':'user2', 'password':'password', 'firstname':'John', 'lastname':'Doe'}
    r=requests.post('http://werewolf-jpblair.herokuapp.com/newuser', data=payload)
    print(r.text)
    '''
    
    '''
    payload={'numMinutes':'5'}
    r=requests.post('http://localhost:8080/werewolf/newgame', auth=('test_user','test_pw'), data=payload)
    print(r.text)
    '''