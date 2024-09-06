package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MoneyTrackr.MoneyTrackr.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByEmail(String email);

	User findByEmail(String email);

}
