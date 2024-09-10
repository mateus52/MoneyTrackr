package com.MoneyTrackr.MoneyTrackr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "users")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;
	
	@Column(name = "user_name",unique=true)
	private String userName;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@Column(name = "email",unique=true)
	private String email;
		
	@Column(name = "document",unique=true)
	private String document;
	
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Expenses> expenses = new HashSet<>();
	 
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Incomes> incomes = new HashSet<>();
	
	public Users(Long userID, String userName, String password, String email, String document, String phone) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.document = document;
		this.phone = phone;
	}

}
