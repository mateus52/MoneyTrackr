package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MoneyTrackr.MoneyTrackr.entity.Incomes;

@Repository
public interface IncomesRepository extends JpaRepository<Incomes, Long>{
	
	@Query(value = "select sum(amount) from incomes where "
			+ "income_data >= :initialDate and income_data <= :finalDate and userid =:idUser ",nativeQuery = true)
	public Double sumIncomes(@Param("initialDate") String initialDate, @Param("finalDate") String finalDate,
			@Param("idUser") Long idUser);

}
