package com.MoneyTrackr.MoneyTrackr.entity;

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
@Table(name= "expenses")
public class Expenses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "expense_id")
	private Long expenseID;
	
	@Column(name = "expense_name")
	private String expenseName;
	
	@Column(name = "expense_amount")
	private Double amount;
	
	@Column(name = "expense_data")
	private String expenseData;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	
}
