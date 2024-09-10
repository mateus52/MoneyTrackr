package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.NewUserDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.exception.BadRequestException;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder pe;

	public Users findUserByID(Long id) {

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

	    return repository.save(user);

	}

	public void alterUser(Long id, NewUserDTO obj) {
		boolean idExists = repository.existsById(id);
		
		if(!idExists) {
			throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
		}

		Users user = modelMapper.map(obj, Users.class);
		user.setUserID(id);
		user.setPassword(pe.encode(obj.getPassword()));
		repository.save(user);
	}
	
	public void deleteUser(Long id) {
		boolean exists = repository.existsById(id);
		if(!exists) {
			throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
		}
		repository.deleteById(id);

	}

}
