package edu.wm.werewolf.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.MyUser;
import edu.wm.werewolf.domain.User;

public class UserServiceImpl implements IUserService, UserDetailsService {
	
	@Autowired private IUserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

			User u = userDAO.getUserByUsername(username);
			return new MyUser(u.getUsername(), u.getHashedPassword(), u.getFirstname(), u.getLastname(), u.getImageURL());
		
	}

	@Override
	public void createUser(String username, String password) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User u = new User(username, passwordEncoder.encode(password));
		userDAO.insertUser(u);
		
	}

	@Override
	public void removeUser(String username) {
		// TODO Auto-generated method stub
		
	}



}
