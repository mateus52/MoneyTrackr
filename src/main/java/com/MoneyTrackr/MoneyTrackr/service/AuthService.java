package com.MoneyTrackr.MoneyTrackr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.AcessDTO;
import com.MoneyTrackr.MoneyTrackr.dto.AuthenticationRequestDTO;
import com.MoneyTrackr.MoneyTrackr.dto.UserDetailsImpl;
import com.MoneyTrackr.MoneyTrackr.security.jwt.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil util;

	public AcessDTO login(AuthenticationRequestDTO dto) {
		
		try {
			UsernamePasswordAuthenticationToken userAuth = 
					new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
			Authentication authentication =  manager.authenticate(userAuth);
			
			UserDetailsImpl userDetailsServiceImpl = (UserDetailsImpl) authentication.getPrincipal();
			
			String token = util.generateTokenFromUserDetailsImpl(userDetailsServiceImpl);
			
			AcessDTO acessDTO = new AcessDTO(token);
			
			return acessDTO;
			
		}catch (BadCredentialsException e) {
			System.out.println("Erro " + e.getMessage() );
		}
		return new AcessDTO("Acesso negado");
		
		
	}
}
