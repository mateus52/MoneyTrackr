package com.MoneyTrackr.MoneyTrackr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name= "incomes")
public class Incomes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "income_id")
	private Long incomeID;
	
	@Column(name = "income_name")
	private String incomeName;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "income_data")
	private String incomeData;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "userid")
	private Users user;

}
