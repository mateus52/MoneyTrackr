package com.MoneyTrackr.MoneyTrackr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MoneyTrackr.MoneyTrackr.entity.Expenses;


public interface ExpenseRepository extends JpaRepository<Expenses, Long>{

}
