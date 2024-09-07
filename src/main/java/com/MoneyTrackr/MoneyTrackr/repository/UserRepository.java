package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MoneyTrackr.MoneyTrackr.entity.Users;


public interface UserRepository extends JpaRepository<Users, Long>{
	
	boolean existsByEmail(String email);

	Users findByEmail(String email);
	
	boolean existsByUserName(String userName);
	
	boolean existsByDocument(String document);

}
