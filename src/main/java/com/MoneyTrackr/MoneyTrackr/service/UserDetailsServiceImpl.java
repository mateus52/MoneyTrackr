package com.MoneyTrackr.MoneyTrackr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.UserDetailsImpl;
import com.MoneyTrackr.MoneyTrackr.entity.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userService.findUserByUserName(username);
		
		return UserDetailsImpl.build(user);
		
	}
	
	

}
