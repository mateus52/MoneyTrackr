package com.MoneyTrackr.MoneyTrackr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.MoneyTrackr.MoneyTrackr.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name= "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userID;
	
	private String userName;
	
	@JsonIgnore
	private String password;
	
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="profile")
	private Set<Long> profiles = new HashSet<>();
	
	@OneToMany(mappedBy = "user	", cascade = CascadeType.ALL)
	private Set<Expenses> expenses;
	 
	@OneToMany(mappedBy = "user	", cascade = CascadeType.ALL)
	private Set<Incomes> incomes;
	
	public User(Long userID, String userName, String password, String email) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
	}

}
