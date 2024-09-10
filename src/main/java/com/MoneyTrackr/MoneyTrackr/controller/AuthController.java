package com.MoneyTrackr.MoneyTrackr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MoneyTrackr.MoneyTrackr.dto.AuthenticationRequestDTO;
import com.MoneyTrackr.MoneyTrackr.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value= "/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO dto){
		return ResponseEntity.ok(authService.login(dto));
	}
	
}
	

