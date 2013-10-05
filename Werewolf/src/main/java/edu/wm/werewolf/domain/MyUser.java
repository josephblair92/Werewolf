package edu.wm.werewolf.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
	
	private String firstname;
	private String lastname;
	private String imageURL;
	private int score;

	public MyUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String firstname, String lastname,
			String imageURL) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
		this.firstname=firstname;
		this.lastname=lastname;
		this.imageURL=imageURL;
		score=0;
		
	}
	
	public MyUser(String username, String password, String firstname, String lastname,
			String imageURL) {
		super(username, password, true, true, true, true, Arrays.asList(new GrantedAuthority[] {new SimpleGrantedAuthority("ROLE_USER")}));
		this.firstname=firstname;
		this.lastname=lastname;
		this.imageURL=imageURL;
		score=0;
	}
	
	public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstname, String lastname,
			String imageURL) {
		super(username, password, true, true, true, true, authorities);
		this.firstname=firstname;
		this.lastname=lastname;
		this.imageURL=imageURL;
		score=0;
	}

}
