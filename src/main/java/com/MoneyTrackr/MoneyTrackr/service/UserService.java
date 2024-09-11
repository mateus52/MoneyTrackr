package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.NewUserDTO;
import com.MoneyTrackr.MoneyTrackr.dto.UserDetailsImpl;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.exception.BadRequestException;
import com.MoneyTrackr.MoneyTrackr.exception.DataIntegrityException;
import com.MoneyTrackr.MoneyTrackr.exception.ForbiddenException;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder pe;
	
	public static UserDetailsImpl authenticated() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

	public Users findUserByID(Long id) {
		
		UserDetailsImpl user = UserService.authenticated();
		if (user==null || !id.equals(user.getId())) {
			throw new ForbiddenException("Acesso negado");
		}

		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found: " + id));
	}

	public Users findUserByEmail(String email) {
		
		Users user = repository.findByEmail(email);
		if (user == null) {
			throw new NotFoundException("User not found: " + email);
		}

		return user;
	}
	
	public Users findUserByUserName(String userName) {
		
		UserDetailsImpl obj = UserService.authenticated();
		if (obj==null || !userName.equals(obj.getUsername())) {
			throw new ForbiddenException("Acesso negado");
		}

		Users user = repository.findByUserName(userName);
		if (user == null) {
			throw new NotFoundException("User not found: " + userName);
		}

		return user;
	}

	public List<Users> findAll() {
		return repository.findAll();
	}

	public Users insertUser(NewUserDTO obj) {
		
		boolean emailExists  = repository.existsByEmail(obj.getEmail());
		boolean documentExists  = repository.existsByDocument(obj.getDocument());
		boolean usernameExists  = repository.existsByUserName(obj.getUserName());
		
		StringBuilder errorMessage = new StringBuilder();

	    if (emailExists) {
	        errorMessage.append("A user with this email is already registered. ");
	    }

	    if (documentExists) {
	        errorMessage.append("A user with this document is already registered. ");
	    }

	    if (usernameExists) {
	        errorMessage.append("A user with this userName is already registered.");
	    }

	    if (errorMessage.length() > 0) {
	        throw new BadRequestException(errorMessage.toString().trim());
	    }

	    Users user = new Users(null, obj.getUserName(), pe.encode(obj.getPassword()), obj.getEmail(), obj.getDocument(), obj.getPhone());

	    return save(user);

	}

	public void alterUser(Long id, NewUserDTO obj) {
		
		Users user =  findUserByID(id);
		
		user.setEmail(obj.getEmail());
		user.setPhone(obj.getPassword());

		save(user);
	}
	
	public void deleteUser(Long id) {
		
		findUserByID(id);
		
		try {
		
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete because there are related expenses or incomes");
		}
	}

	public Users save(Users user) {
		return repository.save(user);
		
	}
	
}
