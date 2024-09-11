package com.MoneyTrackr.MoneyTrackr.service;

import org.springframework.mail.SimpleMailMessage;

import com.MoneyTrackr.MoneyTrackr.entity.Users;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Users user, String newPass);
}
