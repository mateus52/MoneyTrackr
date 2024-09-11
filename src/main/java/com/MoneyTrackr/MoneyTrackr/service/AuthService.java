package com.MoneyTrackr.MoneyTrackr.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.AcessDTO;
import com.MoneyTrackr.MoneyTrackr.dto.AuthenticationRequestDTO;
import com.MoneyTrackr.MoneyTrackr.dto.UserDetailsImpl;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.security.jwt.JwtUtil;

import jakarta.validation.Valid;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

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

	public void sendNewPassword(@Valid String email) {
		Users user = userService.findUserByEmail(email);
		
		String newPass = newPassword();
		user.setPassword(pe.encode(newPass));
		
		userService.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
		
	}
	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	
	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
