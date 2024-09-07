package com.MoneyTrackr.MoneyTrackr.dto;

import java.io.Serializable;
import java.util.Set;

import com.MoneyTrackr.MoneyTrackr.entity.Expenses;
import com.MoneyTrackr.MoneyTrackr.entity.Incomes;
import com.MoneyTrackr.MoneyTrackr.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private String userName;
		
		private String email;
		
		private String document;
		
		private String phone;
		
		private Set<Expenses> expenses;
		 
		private Set<Incomes> incomes;
		
		public UserDTO(Users obj) {
			userName = obj.getUserName();
			email = obj.getEmail();
			document =obj.getDocument();
			phone = obj.getPhone();
			expenses = obj.getExpenses();
			incomes = obj.getIncomes();
		}
		
}
