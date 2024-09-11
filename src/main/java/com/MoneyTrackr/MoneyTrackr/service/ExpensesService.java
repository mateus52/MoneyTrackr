package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.ExpenseDTO;
import com.MoneyTrackr.MoneyTrackr.dto.PeriodDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Expenses;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.ExpenseRepository;

@Service
public class ExpensesService {

		@Autowired
		private ExpenseRepository repository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		@Autowired
		private UserService userService;
		

		public Expenses findExpenseByID(Long id) {
			return repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Expense not found: " + id));
		}

		public List<Expenses> findAllExpenses() {
			return repository.findAll();
		}	

		public Expenses insertExpenses(@Valid ExpenseDTO dto) {
			
			Users user = userService.findUserByID(dto.getUser());
				
			Expenses expense = modelMapper.map(dto, Expenses.class);
			expense.setUser(user);
				
			return repository.save(expense);
			
		}
			
		public void deleteExpense(Long id) {

			findExpenseByID(id);

			repository.deleteById(id);

		}
		
		public void alterExpense(Long id, @Valid ExpenseDTO dto) {

			Expenses expense = findExpenseByID(id);
			expense.setAmount(dto.getAmount());
			expense.setExpenseName(dto.getExpenseName());
			expense.setExpenseData(dto.getExpenseData());

			repository.save(expense);
		}
		
		public Double sumExpenses(PeriodDTO dto, Long id) {

			userService.findUserByID(id);

			return repository.sumExpenses(dto.getInitialData(), dto.getFinalData(), id);
		}
		
		

	}


