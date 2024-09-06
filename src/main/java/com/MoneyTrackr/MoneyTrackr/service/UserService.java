package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.entity.User;
import com.MoneyTrackr.MoneyTrackr.exception.BadRequestException;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;


	public User findUserByID(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found: " + id));
	}

	public User findUserByEmail(String email) {

		User user = repository.findByEmail(email);
		if (user == null) {
			throw new NotFoundException("User not found: " + email);
		}

		return user;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User inserirUsuario(User obj) {
		boolean exists = repository.existsByEmail(obj.getEmail());
		if (exists) {
			throw new BadRequestException("A user with this email is already registered.");
		}

		User user = modelMapper.map(obj, User.class);

		return repository.save(user);
	}

	public void alterUser(Long id, User obj) {
		boolean exists = repository.existsById(id);
		if (!exists) {
			throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
		}

		User user = modelMapper.map(obj, User.class);
		user.setUserID(id);
		repository.save(user);
	}

}
