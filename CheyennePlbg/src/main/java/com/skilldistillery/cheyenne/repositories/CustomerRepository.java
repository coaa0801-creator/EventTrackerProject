package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
