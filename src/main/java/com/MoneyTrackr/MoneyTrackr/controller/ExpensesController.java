package com.MoneyTrackr.MoneyTrackr.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.MoneyTrackr.MoneyTrackr.dto.ExpenseDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Expenses;
import com.MoneyTrackr.MoneyTrackr.service.ExpensesService;


@RestController
@RequestMapping("/api/v1/expenses")
public class ExpensesController {
	
	@Autowired
	private ExpensesService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Expenses> findExpenseId(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findExpenseByID(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ExpenseDTO>> listExpenses(){
		List<Expenses> list = service.findAllExpenses();
		List<ExpenseDTO> listDto = list.stream().map(obj -> new ExpenseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Expenses> insertExpense(@Valid @RequestBody ExpenseDTO dto) {
		Expenses expense = service.insertExpenses(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(expense.getExpenseID()).toUri();
		
		return ResponseEntity.created(location).body(expense);
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> alterExpanse(@Valid @RequestBody ExpenseDTO dto, @PathVariable Long id){
		service.alterExpense(id, dto);
		return ResponseEntity.noContent().build();
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
		service.deleteExpense(id);
		return ResponseEntity.noContent().build();
	}

}
