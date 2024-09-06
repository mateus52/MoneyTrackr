package com.MoneyTrackr.MoneyTrackr.dto;

import java.io.Serializable;

import com.MoneyTrackr.MoneyTrackr.entity.Expenses;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private Long expenseID;
		
		@NotBlank
		private String expenseName;
		
		@NotNull(message = "Amount cannot be null")
	    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
		private Double amount;
		
		@NotBlank
		private String expenseData;
		
		@NotBlank
		private Long user;
		
		public ExpenseDTO(Expenses obj) {
			expenseID = obj.getExpenseID();
			expenseName = obj.getExpenseName();
			amount = obj.getAmount();
			expenseData = obj.getExpenseData();
			user = obj.getUser().getUserID();
		}
		
}
