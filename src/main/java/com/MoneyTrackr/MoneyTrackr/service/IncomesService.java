package com.MoneyTrackr.MoneyTrackr.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoneyTrackr.MoneyTrackr.dto.IncomeDTO;
import com.MoneyTrackr.MoneyTrackr.entity.Incomes;
import com.MoneyTrackr.MoneyTrackr.entity.Users;
import com.MoneyTrackr.MoneyTrackr.exception.BadRequestException;
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
			boolean exists = repository.existsById(id);
			if(!exists) {
				throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
			}
			repository.deleteById(id);

		}
		
		public void alterincomes(Long id, @Valid IncomeDTO dto) {
			boolean exists = repository.existsById(id);
			if(!exists) {
				throw new BadRequestException("It was not possible to change ID " + id +" nonexistent");
			}
			
			Users user = userService.findUserByID(dto.getUser());
			
			Incomes income = modelMapper.map(dto, Incomes.class);
			income.setIncomeID(id);
			income.setUser(user);
			repository.save(income);
		}
		
		

	}


