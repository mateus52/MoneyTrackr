package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MoneyTrackr.MoneyTrackr.entity.Expenses;


@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long>{

	@Query(value = "select sum(expense_amount) from expenses where "
			+ "expense_data >= :initialDate and expense_data <= :finalDate and userid =:idUser ",nativeQuery = true)
	public Double sumExpenses(@Param("initialDate") String initialDate, @Param("finalDate") String finalDate,
			@Param("idUser") Long idUser);

}
