package com.MoneyTrackr.MoneyTrackr.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.MoneyTrackr.MoneyTrackr.entity.Users;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendNewPasswordEmail(Users user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Users user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Request new password");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("new password: " + newPass);
		return sm;
	}

}
