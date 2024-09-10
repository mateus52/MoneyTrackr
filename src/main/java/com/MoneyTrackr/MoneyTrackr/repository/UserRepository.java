package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoneyTrackr.MoneyTrackr.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	boolean existsByEmail(String email);

	Users findByEmail(String email);
	
	boolean existsByUserName(String userName);
	
	Users findByUserName(String userName);
	
	boolean existsByDocument(String document);

}
