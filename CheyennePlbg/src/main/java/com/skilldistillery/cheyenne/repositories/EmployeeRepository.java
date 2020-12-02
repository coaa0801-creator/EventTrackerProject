package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
