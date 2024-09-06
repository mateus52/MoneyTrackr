package com.MoneyTrackr.MoneyTrackr.dto;

import java.io.Serializable;

import com.MoneyTrackr.MoneyTrackr.entity.Incomes;

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
public class IncomeDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private Long incomeID;
		
		@NotBlank
		private String incomeName;
		
		@NotNull(message = "Amount cannot be null")
	    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")	
		private Double amount;
		
		@NotBlank
		private String incomeData;
		
		@NotBlank
		private Long user;
		
		public IncomeDTO(Incomes obj) {
			incomeID = obj.getIncomeID();
			incomeName = obj.getIncomeName();
			amount = obj.getAmount();
			incomeData = obj.getIncomeData();
			user = obj.getUser().getUserID();
		}
		
}
