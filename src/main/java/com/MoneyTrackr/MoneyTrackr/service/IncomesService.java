package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.IncomeDTO;
import com.MoneyTrackr.MoneyTrackr.dto.PeriodDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Incomes;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.exception.NotFoundException;
import com.MoneyTrackr.MoneyTrackr.repository.IncomesRepository;

@Service
public class IncomesService {

		@Autowired
		private IncomesRepository repository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		@Autowired
		private UserService userService;
		

		public Incomes findincomesByID(Long id) {
			return repository.findById(id)
					.orElseThrow(() -> new NotFoundException("incomes not found: " + id));
		}

		public List<Incomes> findAllIncomes() {
			return repository.findAll();
		}	

		public Incomes insertIncomes(@Valid IncomeDTO dto) {
			
			Users user = userService.findUserByID(dto.getUser());
				
			Incomes incomes = modelMapper.map(dto, Incomes.class);
			incomes.setUser(user);
				
			return repository.save(incomes);
			
		}
			
		public void deleteincomes(Long id) {
			
			findincomesByID(id);
			
			repository.deleteById(id);

		}
		
		public void alterincomes(Long id, @Valid IncomeDTO dto) {
			
			Incomes income = findincomesByID(id);
			income.setAmount(dto.getAmount());
			income.setIncomeName(dto.getIncomeName());
			income.setIncomeData(dto.getIncomeData());
			repository.save(income);
		}

		public Double sumIncomes(@Valid PeriodDTO dto, Long id) {

			userService.findUserByID(id);

			return repository.sumIncomes(dto.getInitialData(), dto.getFinalData(), id);
		}
		
		

	}


