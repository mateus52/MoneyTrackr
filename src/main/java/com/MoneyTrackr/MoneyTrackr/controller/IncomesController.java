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

import com.MoneyTrackr.MoneyTrackr.dto.IncomeDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Incomes;
import com.MoneyTrackr.MoneyTrackr.service.IncomesService;


@RestController
@RequestMapping("/api/v1/incomes")
public class IncomesController {
	
	@Autowired
	private IncomesService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Incomes> findIncomeId(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findincomesByID(id));
	}
	
	@GetMapping
	public ResponseEntity<List<IncomeDTO>> listIncomes(){
		List<Incomes> list = service.findAllIncomes();
		List<IncomeDTO> listDto = list.stream().map(obj -> new IncomeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Incomes> insertIncome(@Valid @RequestBody IncomeDTO dto) {
		Incomes income = service.insertIncomes(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(income.getIncomeID()).toUri();
		
		return ResponseEntity.created(location).body(income);
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> alterIncome(@Valid @RequestBody IncomeDTO dto, @PathVariable Long id){
		service.alterincomes(id, dto);
		return ResponseEntity.noContent().build();
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
		service.deleteincomes(id);
		return ResponseEntity.noContent().build();
	}

}
