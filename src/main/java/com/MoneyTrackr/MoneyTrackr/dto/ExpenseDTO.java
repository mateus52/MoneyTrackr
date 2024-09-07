package com.MoneyTrackr.MoneyTrackr.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.MoneyTrackr.MoneyTrackr.entity.Expenses;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
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
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(max=120, message="O tamanho deve ate 120 caracteres")
		private String expenseName;
		
		@NotNull(message = "Amount cannot be null")
	    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
		private Double amount;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=10, max=10, message="O tamanho deve ter 10 caracteres")
		private String expenseData;
		
		@NotNull(message = "User ID cannot be null")
		private Long user;
		
		public ExpenseDTO(Expenses obj) {
			expenseName = obj.getExpenseName();
			amount = obj.getAmount();
			expenseData = obj.getExpenseData();
			user = obj.getUser().getUserID();
		}
		
}
