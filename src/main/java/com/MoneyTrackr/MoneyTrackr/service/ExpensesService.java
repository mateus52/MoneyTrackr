package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.ExpenseDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Expenses;
import com.MoneyTrackr.MoneyTrackr.exception.BadRequestException;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.ExpenseRepository;

@Service
public class ExpensesService {

		@Autowired
		private ExpenseRepository repository;
		
		@Autowired
		private ModelMapper modelMapper;
		

		public Expenses findExpenseByID(Long id) {
			return repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Expense not found: " + id));
		}

		public List<Expenses> findAllExpenses() {
			return repository.findAll();
		}	

		public Expenses insertExpenses(@Valid ExpenseDTO dto) {
				
			Expenses expense = modelMapper.map(dto, Expenses.class);
				
			return repository.save(expense);
			
		}
			
		public void deleteExpense(Long id) {
			boolean exists = repository.existsById(id);
			if(!exists) {
				throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
			}
			repository.deleteById(id);

		}
		
		public void alterExpense(Long id, @Valid ExpenseDTO dto) {
			boolean exists = repository.existsById(id);
			if(!exists) {
				throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
			}
			
			Expenses expense = modelMapper.map(dto, Expenses.class);
			expense.setExpenseID(id);
			repository.save(expense);
		}
		
		

	}


