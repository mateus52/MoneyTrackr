package com.MoneyTrackr.MoneyTrackr.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.MoneyTrackr.MoneyTrackr.dto.NewUserDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.service.UserService;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/byID/{id}")
	public ResponseEntity<Users> findUserId(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findUserByID(id));
	}
	
	@GetMapping(value = "/byEmail/{email}")
	public ResponseEntity<Users> findUserByEmail(@PathVariable String email){
		return ResponseEntity.ok().body(service.findUserByEmail(email));
	}
	
	@GetMapping
	public ResponseEntity<List<Users>> listUsers(){
		List<Users> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Users> insertUser(@Valid @RequestBody NewUserDTO dto) {
		Users user = service.insertUser(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(user.getUserID()).toUri();
		
		return ResponseEntity.created(location).body(user);
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> alterUser(@Valid @RequestBody NewUserDTO dto, @PathVariable Long id){
		service.alterUser(id, dto);
		return ResponseEntity.noContent().build();
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
